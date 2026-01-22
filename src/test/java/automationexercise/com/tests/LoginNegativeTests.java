package automationexercise.com.tests;

import automationexercise.com.infra.base.BaseTest;
import automationexercise.com.steps.LoginSteps;
import automationexercise.com.utils.ConfigReader;
import common.infra.ConsoleReporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginNegativeTests extends BaseTest {

    private LoginSteps loginSteps;

    @BeforeMethod
    public void setUpTest() {
        loginSteps = new LoginSteps(page);
        loginSteps.logout();
    }

    @Test(description = "Verify login with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        String invalidUsername = ConfigReader.getEnv("INVALID_USERNAME");
        String invalidPassword = ConfigReader.getEnv("INVALID_PASSWORD");
        ConsoleReporter.log("STEP: Attempting invalid login");
        loginSteps.invalidLogin(invalidUsername, invalidPassword);
        String referenceText = "Your email or password is incorrect!";
        ConsoleReporter.log("STEP: Verifying error message");
        loginSteps.verifyErrorMessage(referenceText);
    }


}

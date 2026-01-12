package automationexercise.com.tests;
//import automationexercise.com.infra.ConsoleReporter;
import automationexercise.com.steps.LoginSteps;
import automationexercise.com.utils.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginNegativeTests extends BaseTests {

    private LoginSteps loginSteps;

    @BeforeMethod
    public void setUpTest() {
        loginSteps = new LoginSteps(page);
        loginSteps.logout();
    }

    @Test(description = "Verify login with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        //ConsoleReporter.log("Step 1 - Attempt login with invalid credentials");

        // Getting invalid credentials from .env via ConfigReader
        String invalidUsername = ConfigReader.getEnv("INVALID_USERNAME");
        String invalidPassword = ConfigReader.getEnv("INVALID_PASSWORD");
        loginSteps.invalidLogin(invalidUsername, invalidPassword);

       // ConsoleReporter.log("Step 2 - Verify error message is displayed");
        String referenceText = "Your email or password is incorrect!";
        loginSteps.verifyErrorMessage(referenceText);
    }


}

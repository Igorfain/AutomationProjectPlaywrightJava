package automationexercise.com.tests;

import automationexercise.com.infra.ConsoleReporter;
import automationexercise.com.steps.LoginSteps;
import automationexercise.com.steps.MainPageSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests {

    private MainPageSteps mainPageSteps;
    private LoginSteps loginSteps;

    @BeforeMethod
    public void setUpTest() {
        mainPageSteps= new MainPageSteps(page);
        loginSteps = new LoginSteps(page);
    }

    @Test(description = "Verify login ")
    public void testLogin() {
        ConsoleReporter.log("Step 1 - Verify login in main page");
        String expectedUserText = "TestUser";
        mainPageSteps.verifyLoggedInUser(expectedUserText);

    }


}

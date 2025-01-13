package automationexercise.com.tests;

import automationexercise.com.infra.ConsoleReporter;
import automationexercise.com.steps.MainPageSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends BaseTests {

    private MainPageSteps mainPageSteps;

    @BeforeMethod
    public void setUpTest() {
        mainPageSteps= new MainPageSteps(page);
    }

    @Test(description = "Verify login ")
    public void testLogin() {
        ConsoleReporter.log("Step 1 - Verify login in main page");
        String expectedUserText = "TestUser111";
        mainPageSteps.verifyLoggedInUser(expectedUserText);

    }
}

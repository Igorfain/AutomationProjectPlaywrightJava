package automationexercise.com.tests;

import automationexercise.com.steps.LoginSteps;
import automationexercise.com.steps.MainPageSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainPageTests extends BaseTest {

    private MainPageSteps mainPageSteps;
    private LoginSteps loginSteps;

    @BeforeMethod
    public void setUpTest() {
        mainPageSteps= new MainPageSteps(page);
        loginSteps = new LoginSteps(page);
    }

    @Test(description = "Verify main page logo")
    public void mainPageLogo(){
        mainPageSteps.verifyMainPageLogoIsVisible();
    }
}

package automationexercise.com.tests;

import automationexercise.com.infra.base.BaseTest;
import automationexercise.com.steps.MainPageSteps;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Story("Main Page")
public class MainPageTests extends BaseTest {

    private MainPageSteps mainPageSteps;

    @BeforeMethod
    public void setUpTest() {
        mainPageSteps= new MainPageSteps(page);
    }

    @Test(description = "Verify main page logo")
    public void mainPageLogo(){
        mainPageSteps.verifyMainPageLogoIsVisible();
    }
}

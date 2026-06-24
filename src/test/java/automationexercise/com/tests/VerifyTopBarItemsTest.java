package automationexercise.com.tests;

import automationexercise.com.infra.base.BaseTest;
import automationexercise.com.steps.MainPageSteps;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Story("Top Bar")
public class VerifyTopBarItemsTest extends BaseTest {

    private MainPageSteps mainPageSteps;

    @BeforeMethod
    public void setUpTest() {
        mainPageSteps = new MainPageSteps(page);
    }

    @Test(description = "Verify top bar items are present in main page")
    public void verifyTopBarItemsWhenLoggedIn() {
        mainPageSteps.verifyTopBarItemsWhenLoggedIn();
    }
}

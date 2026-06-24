package automationexercise.com.steps;

import automationexercise.com.infra.testdata.TopBarData;
import automationexercise.com.pages.MainPage;
import com.microsoft.playwright.Page;
import common.infra.ConsoleReporter;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MainPageSteps {

    private final MainPage mainPage;

    public MainPageSteps(Page page) {
        this.mainPage = new MainPage(page);
    }

    @Step("Verify logged in user in main page")
    public MainPageSteps verifyLoggedInUser(String expectedUserText) {
        ConsoleReporter.log("Verify logged in user in main page");
        String actualLoggedInUserText = mainPage.getLoggedInUserText();
        Assert.assertTrue(
                actualLoggedInUserText.contains(expectedUserText),
                "Expected '" + expectedUserText + "' in logged-in user text, but got: " + actualLoggedInUserText
        );
        return this;
    }

    @Step("Verify Main page logo is visible")
    public MainPageSteps verifyMainPageLogoIsVisible() {
        ConsoleReporter.log("Verify Main page logo is visible");
        assertThat(mainPage.logo).isVisible();
        return this;
    }

    @Step("Verify top bar items in main page")
    public MainPageSteps verifyTopBarItemsWhenLoggedIn() {
        ConsoleReporter.log("Verify top bar items when logged in");

        List<String> expectedTopBarItems = TopBarData.getExpectedTopBarItemsWhenLoggedIn();
        List<String> actualTopBarItems = mainPage.getTopBarItemTexts();

        for (String expectedTopBarItem : expectedTopBarItems) {
            boolean navigationBarItemFound = false;

            for (String actualTopBarItem : actualTopBarItems) {
                if (actualTopBarItem.contains(expectedTopBarItem)) {
                    navigationBarItemFound = true;
                    break;
                }
            }

            Assert.assertTrue(
                    navigationBarItemFound,
                    "Navigation bar item containing '" + expectedTopBarItem
                            + "' was not found. Actual navigation bar items: "
                            + actualTopBarItems
            );
        }

        return this;
    }
}

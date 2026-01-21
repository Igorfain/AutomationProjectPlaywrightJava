package automationexercise.com.steps;

import automationexercise.com.pages.MainPage;
import com.microsoft.playwright.Page;
import common.infra.ConsoleReporter;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MainPageSteps {

    private final MainPage mainPage;

    public MainPageSteps(Page page) {
        this.mainPage = new MainPage(page);
    }


    @Step("Verify logged in user in main page")
    public void verifyLoggedInUser(String expectedUserText) {
        ConsoleReporter.log("STEP: Verify logged in user in main page");
        mainPage.verifyLoggedInUser(expectedUserText);
        mainPage.getLoggedInUserText();
        mainPage.verifyLoggedInUser(expectedUserText);
    }

    @Step("Verify Main page logo is visible")
    public void verifyMainPageLogoIsVisible(){
        ConsoleReporter.log("STEP: Verify Main page logo is visible");
        assertThat(mainPage.logo).isVisible();
    }

}

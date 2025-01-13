package automationexercise.com.steps;

import automationexercise.com.pages.MainPage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class MainPageSteps {

    private final MainPage mainPage;

    public MainPageSteps(Page page) {
        this.mainPage = new MainPage(page);
    }


    @Step("Verify logged in user in main page")
    public void verifyLoggedInUser(String expectedUserText) {
        Allure.step("Verify logged in user in main page");
        mainPage.verifyLoggedInUser(expectedUserText);
        mainPage.getLoggedInUserText();
        mainPage.verifyLoggedInUser(expectedUserText);
    }

}

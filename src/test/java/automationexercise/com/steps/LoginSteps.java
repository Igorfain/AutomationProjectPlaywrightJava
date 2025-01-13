package automationexercise.com.steps;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import automationexercise.com.pages.LoginPage;

public class LoginSteps {

    private final LoginPage loginPage;

    public LoginSteps(Page page) {
        this.loginPage = new LoginPage(page);
    }

    @Step("Enter credentials and login")
    public void login(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }
}

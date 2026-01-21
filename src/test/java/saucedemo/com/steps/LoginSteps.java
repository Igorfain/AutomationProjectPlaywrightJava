package saucedemo.com.steps;

import com.microsoft.playwright.Page;
import common.infra.ConsoleReporter;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import saucedemo.com.pages.LoginPage;

public class LoginSteps {

    private final LoginPage loginPage;

    public LoginSteps(Page page) {
        this.loginPage = new LoginPage(page);
    }

    @Step("Enter credentials and login")
    public void login(String username, String password) {
        ConsoleReporter.log("Logging in with username");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }
}

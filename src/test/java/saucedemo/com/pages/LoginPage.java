package saucedemo.com.pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    // Locators
    private String usernameSelector = "#user-name";
    private String passwordSelector = "#password";
    private String loginButtonSelector = "#login-button";
    private String errorMessageSelector = "div.error-message-container";

    public LoginPage(Page page) {
        super(page); // Call the constructor of BasePage
    }
    public void enterUsername(String username) {
        page.locator(usernameSelector).fill(username);
    }

    public void enterPassword(String password) {
        page.locator(passwordSelector).fill(password);
    }

    public void clickLoginButton() {
        page.locator(loginButtonSelector).click();


    }
}

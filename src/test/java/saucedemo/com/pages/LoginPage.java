package saucedemo.com.pages;

import com.microsoft.playwright.Page;
import saucedemo.com.infra.actions.ActionBot;

public class LoginPage extends BasePage {

    // Locators
    private static final String USERNAME_SELECTOR = "#user-name";
    private static final String PASSWORD_SELECTOR = "#password";
    private static final String LOGIN_BUTTON_SELECTOR = "#login-button";
    private static final String ERROR_MESSAGE_SELECTOR = "div.error-message-container";
    private final ActionBot actionBot;

    public LoginPage(Page page) {
        super(page); // Call the constructor of BasePage
        this.actionBot = new ActionBot(page); // Instantiate the ActionBot
    }

    // With the ActionBot, we can now use the fill method to fill in the username
    public LoginPage enterUsername(String username) {
        //  page.locator(usernameSelector).fill(username);  *//example to use actionBot - Igor
        actionBot.fill(USERNAME_SELECTOR, username);
        return this;
    }

    // Without the ActionBot, we would have to use the fill method directly on the page object-password field.
    public LoginPage enterPassword(String password) {
        page.locator(PASSWORD_SELECTOR).fill(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        page.locator(LOGIN_BUTTON_SELECTOR).click();
        return this;
    }
}

package saucedemo.com.pages;

import com.microsoft.playwright.Page;
import saucedemo.com.infra.actions.ActionBot;

public class LoginPage extends BasePage {

    // Locators
    private String usernameSelector = "#user-name";
    private String passwordSelector = "#password";
    private String loginButtonSelector = "#login-button";
    private String errorMessageSelector = "div.error-message-container";
    private ActionBot actionBot;

    public LoginPage(Page page) {
        super(page); // Call the constructor of BasePage
        this.actionBot = new ActionBot(page); // Instantiate the ActionBot
    }

    // With the ActionBot, we can now use the fill method to fill in the username
    public void enterUsername(String username) {
        //  page.locator(usernameSelector).fill(username);  *//example to use actionBot - Igor
        actionBot.fill(usernameSelector, username);
    }

    // Without the ActionBot, we would have to use the fill method directly on the page object-password field.
    public void enterPassword(String password) {
        page.locator(passwordSelector).fill(password);
    }

    public void clickLoginButton() {
        page.locator(loginButtonSelector).click();


    }
}

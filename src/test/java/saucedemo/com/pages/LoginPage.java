package saucedemo.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import saucedemo.com.infra.actions.ActionBot;

public class LoginPage extends BasePage {

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        super(page);
        this.usernameInput = page.locator("#user-name");
        this.passwordInput = page.locator("#password");
        this.loginButton = page.locator("#login-button");
        this.errorMessage = page.locator("div.error-message-container");

    }

    // With the ActionBot, we can now use the fill method to fill in the username
    public void enterUsername(String username) {
        usernameInput.fill(username);
    }

    // Without the ActionBot, we would have to use the fill method directly on the page object-password field.
    public void enterPassword(String password) {
        passwordInput.fill(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.innerText();
    }
}

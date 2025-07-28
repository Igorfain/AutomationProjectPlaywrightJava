package automationexercise.com.pages;

import com.microsoft.playwright.Page;
import automationexercise.com.infra.actions.ActionBot;
import org.testng.Assert;
//import automationexercise.com.pages.BasePage;

public class LoginPage extends BasePage {

    // Locators
    private String usernameSelector = "[data-qa='login-email']";
    private String passwordSelector = "[data-qa='login-password']";
    private String loginButtonSelector = "[data-qa='login-button']";
    private String incorrectLoginSelector = "form p[style='color: red;']";
    private String logoutSelector = "a[href='/logout']";


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

    public void clickLogoutButton(){
        page.locator(logoutSelector).click();
    }

    public String getErrorMessage() {
        String actualLoginErrorText = page.locator(incorrectLoginSelector).textContent();
        return actualLoginErrorText;
    }

    public void verifyErrorMessage(String actualLoginErrorText,String referenceText){
        Assert.assertEquals(actualLoginErrorText,referenceText);
    }
}

package automationexercise.com.steps;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import automationexercise.com.pages.LoginPage;

public class LoginSteps {

    private final LoginPage loginPage;

    public LoginSteps(Page page) {
        this.loginPage = new LoginPage(page);
    }

    public void login(String username, String password) {
        Allure.step("Enter credentials and login", () -> {
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();
        });
    }

    public void invalidLogin(String invalidUsername, String invalidPassword) {
        Allure.step("Attempt login with invalid credentials", () -> {
            loginPage.enterUsername(invalidUsername);
            loginPage.enterPassword(invalidPassword);
            loginPage.clickLoginButton();
        });
    }

    public void verifyErrorMessage(String referenceText) {
        Allure.step("Verify error message is displayed", () -> {
            String actualErrorText = loginPage.getErrorMessage();
            loginPage.verifyErrorMessage(actualErrorText, referenceText);
        });
    }

    public void logout() {
        Allure.step("Click on logout button", () -> {
            loginPage.clickLogoutButton();
        });
    }

}


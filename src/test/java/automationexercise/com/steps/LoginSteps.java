package automationexercise.com.steps;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import automationexercise.com.pages.LoginPage;
import org.testng.Assert;
import saucedemo.com.infra.ConsoleReporter;

public class LoginSteps {

    private final LoginPage loginPage;

    public LoginSteps(Page page) {
        this.loginPage = new LoginPage(page);
    }

    @Step("Login")
    public void login(String username, String password) {
        Allure.step("Enter credentials and login", () -> {
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();
        });
    }

    @Step("Invalid Login")
    public void invalidLogin(String invalidUsername, String invalidPassword) {
        Allure.step("Attempt login with invalid credentials", () -> {
            loginPage.enterUsername(invalidUsername);
            loginPage.enterPassword(invalidPassword);
            loginPage.clickLoginButton();
        });
    }

    @Step("Verify Error Message")
    public void verifyErrorMessage(String referenceText) {
        Allure.step("Verify error message is displayed", () -> {
            String actualErrorText = loginPage.getErrorMessage();
            loginPage.verifyErrorMessage(actualErrorText, referenceText);
        });
    }

    @Step("Logout")
    public void logout() {
        Allure.step("Click on logout button", () -> {
            loginPage.clickLogoutButton();
        });
    }

    @Step("Register New User")
    public void registerNewUser(String genderType,String day, String month, String year,String state,String countryValue) {
        String expectedUser = loginPage.signInUserNameInsert();
        loginPage.signInEmailInsert();
        loginPage.clickSignButton();
        loginPage.selectRadioButton(genderType);
        loginPage.signUpPasswordInsert();
        loginPage.dateOfBirthSelect(day,month,year);
        loginPage.selectOptionalCheckboxes();
        loginPage.firstNameInsert();
        loginPage.lastNameInsert();
        loginPage.addressInsert();
        loginPage.scrollDownToElement();
        loginPage.stateInsert(state);
        loginPage.cityInsert();
        loginPage.selectCountry(countryValue);
        loginPage.zipcodeInsert();
        loginPage.mobileNumberInsert();
        loginPage.createAccountClick();
        Assert.assertEquals(loginPage.accountCreatedMessage(),"Account Created!","Message is wrong,please check!");
        loginPage.clickContinueButton();
        Assert.assertEquals(loginPage.actualLoggedInUserName(), expectedUser, "Logged-in username is incorrect!");
        ConsoleReporter.log("User is :"+ expectedUser);


    }
}


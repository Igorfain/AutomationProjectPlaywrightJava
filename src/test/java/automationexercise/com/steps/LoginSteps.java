package automationexercise.com.steps;
import com.microsoft.playwright.Page;
import common.infra.ConsoleReporter;
import io.qameta.allure.Step;
import automationexercise.com.pages.LoginPage;
import org.testng.Assert;

public class LoginSteps {

    private final LoginPage loginPage;

    public LoginSteps(Page page) {
        this.loginPage = new LoginPage(page);
    }

    @Step("Login")
    public void login(String username, String password) {
        ConsoleReporter.log("STEP: Logging in with username: " + username);
            loginPage
                    .enterUsername(username)
                    .enterPassword(password)
                    .clickLoginButton();
    }

    @Step("Invalid Login")
    public void invalidLogin(String invalidUsername, String invalidPassword) {
        ConsoleReporter.log("STEP: Attempting invalid login with username: " + invalidUsername);
            loginPage
                    .enterUsername(invalidUsername)
                    .enterPassword(invalidPassword)
                    .clickLoginButton();

    }

    @Step("Verify Error Message")
    public void verifyErrorMessage(String referenceText) {
        ConsoleReporter.log("STEP: Verifying error message");
            String actualErrorText = loginPage.getErrorMessage();
            loginPage.verifyErrorMessage(actualErrorText, referenceText);

    }

    @Step("Logout")
    public void logout() {
        ConsoleReporter.log("STEP: Logging out from the application");
        loginPage.clickLogoutButton();
    }

    @Step("Register New User")
    public void registerNewUser(String genderType,String day, String month, String year,String state,String countryValue) {
        ConsoleReporter.log("STEP: Registering a new user");
        String expectedUser = loginPage.signInUserNameInsert();
        loginPage
                .signInEmailInsert()
                .clickSignButton()
                .selectRadioButton(genderType)
                .signUpPasswordInsert()
                .dateOfBirthSelect(day, month, year)
                .selectOptionalCheckboxes()
                .firstNameInsert()
                .lastNameInsert()
                .addressInsert()
                .scrollDownToElement()
                .stateInsert(state)
                .cityInsert()
                .selectCountry(countryValue)
                .zipcodeInsert()
                .mobileNumberInsert()
                .createAccountClick();

        Assert.assertEquals(loginPage.accountCreatedMessage(),"Account Created!","Message is wrong,please check!");
        loginPage.clickContinueButton();
        Assert.assertEquals(loginPage.actualLoggedInUserName(), expectedUser, "Logged-in username is incorrect, please check!");
        System.out.println("User is :"+ expectedUser);


    }
}


package automationexercise.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import automationexercise.com.infra.actions.ActionBot;
import org.testng.Assert;
import java.util.Random;
import java.util.UUID;

public class LoginPage extends BasePage {

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator incorrectLoginMessage;
    private final Locator logoutButton;
    private final Locator signInUserNameInput;
    private final Locator signInEmailInput;
    private final Locator signUpButton;
    private final Locator signUpPasswordInput;
    private final Locator daySelect;
    private final Locator monthSelect;
    private final Locator yearSelect;
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator stateInput;
    private final Locator cityInput;
    private final Locator countrySelect;
    private final Locator zipCodeInput;
    private final Locator mobileNumberInput;
    private final Locator createAccountButton;
    private final Locator addressInput;
    private final Locator accountCreatedMessageLocator;
    private final Locator continueButton;

    private final ActionBot actionBot;

    public LoginPage(Page page) {
        super(page);
        this.actionBot = new ActionBot(page);
        this.usernameInput = page.locator("[data-qa='login-email']");
        this.passwordInput = page.locator("[data-qa='login-password']");
        this.loginButton = page.locator("[data-qa='login-button']");
        this.incorrectLoginMessage = page.locator("form p[style='color: red;']");
        this.logoutButton = page.locator("a[href='/logout']");
        this.signInUserNameInput = page.locator("[data-qa='signup-name']");
        this.signInEmailInput = page.locator("[data-qa='signup-email']");
        this.signUpButton = page.locator("[data-qa='signup-button']");
        this.signUpPasswordInput = page.locator("[data-qa='password']");
        this.daySelect = page.locator("#days");
        this.monthSelect = page.locator("#months");
        this.yearSelect = page.locator("#years");
        this.firstNameInput = page.locator("#first_name");
        this.lastNameInput = page.locator("#last_name");
        this.stateInput = page.locator("#state");
        this.cityInput = page.locator("#city");
        this.countrySelect = page.locator("#country");
        this.zipCodeInput = page.locator("#zipcode");
        this.mobileNumberInput = page.locator("#mobile_number");
        this.createAccountButton = page.locator("button[data-qa='create-account']");
        this.addressInput = page.locator("[data-qa='address']");
        this.accountCreatedMessageLocator = page.locator("[data-qa='account-created']");
        this.continueButton = page.locator("[data-qa='continue-button']");
    }

    public LoginPage enterUsername(String username) {
        actionBot.fill(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        actionBot.fill(passwordInput, password);
        return this;
    }

    public LoginPage clickLoginButton() {
        actionBot.click(loginButton);
        return this;
    }

    public LoginPage clickLogoutButton() {
        actionBot.click(logoutButton);
        return this;
    }


    public String getErrorMessage() {
        return actionBot.getText(incorrectLoginMessage);
    }

    public void verifyErrorMessage(String actualLoginErrorText,String referenceText){
        Assert.assertEquals(actualLoginErrorText,referenceText);
    }

    public String signInUserNameInsert() {
        String expectedUser = "test_user" + generateRandomPassword(4);
        actionBot.fill(signInUserNameInput, expectedUser);
        return expectedUser;
    }

    public String actualLoggedInUserName() {
        return page.locator("li b").textContent();
    }

    public LoginPage signInEmailInsert() {
        actionBot.fill(signInEmailInput, generateRandomEmail());
        return this;
    }

    public LoginPage clickSignButton() {
        actionBot.click(signUpButton);
        return this;
    }

    public LoginPage selectRadioButton(String genderType) {
        actionBot.selectRadioButton(page.locator(genderType));
        return this;
    }

    public LoginPage signUpPasswordInsert() {
        actionBot.fill(signUpPasswordInput, generateRandomPassword(4));
        return this;
    }

    public LoginPage dateOfBirthSelect(String day, String month, String year) {
        actionBot.selectOptionByValue(daySelect, day);
        actionBot.selectOptionByText(monthSelect, month);
        actionBot.selectOptionByValue(yearSelect, year);
        return this;
    }

    public LoginPage selectOptionalCheckboxes() {
        actionBot.selectCheckbox(page.locator("#newsletter"));
        actionBot.selectCheckbox(page.locator("#optin"));
        return this;
    }

    public LoginPage firstNameInsert() {
        actionBot.fill(firstNameInput, "test_username" + generateRandomPassword(4));
        return this;
    }

    public LoginPage lastNameInsert() {
        actionBot.fill(lastNameInput, "test_lastname" + generateRandomPassword(4));
        return this;
    }

    public LoginPage addressInsert() {
        actionBot.fill(addressInput, "Bla Bla Bla");
        return this;
    }

    public LoginPage scrollDownToElement() {
        actionBot.scrollToElement(cityInput);
        return this;
    }

    public LoginPage selectCountry(String countryValue) {
        actionBot.selectDropdownByLabel(countrySelect, countryValue);
        return this;
    }

    public LoginPage stateInsert(String state) {
        actionBot.fill(stateInput, state);
        return this;
    }

    public LoginPage cityInsert() {
        actionBot.fill(cityInput, "Sderot");
        return this;
    }

    public LoginPage zipcodeInsert() {
        actionBot.fill(zipCodeInput, "00000000");
        return this;
    }

    public LoginPage mobileNumberInsert() {
        actionBot.fill(mobileNumberInput, "054000000");
        return this;
    }

    public LoginPage createAccountClick() {
        actionBot.click(createAccountButton);
        return this;
    }


    public String accountCreatedMessage(){
       var accountCreatedMessage = actionBot.getText(accountCreatedMessageLocator );
       return accountCreatedMessage;
    }

    public void clickContinueButton(){
        actionBot.click(continueButton);
    }

    public static String generateRandomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 6) + "@test.com";
    }

    public static String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

}

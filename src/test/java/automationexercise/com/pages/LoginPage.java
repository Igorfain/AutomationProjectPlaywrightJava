package automationexercise.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import automationexercise.com.infra.actions.ActionBot;
import org.testng.Assert;
import java.util.Random;
import java.util.UUID;

public class LoginPage extends BasePage {

    // Locators
    private final Locator usernameInput = page.locator("[data-qa='login-email']");
    private final Locator passwordInput = page.locator("[data-qa='login-password']");
    private final Locator loginButton = page.locator("[data-qa='login-button']");
    private final Locator incorrectLoginMessage = page.locator("form p[style='color: red;']");
    private final Locator logoutButton = page.locator("a[href='/logout']");
    private final Locator signInUserNameInput = page.locator("[data-qa='signup-name']");
    private final Locator signInEmailInput = page.locator("[data-qa='signup-email']");
    private final Locator signUpButton = page.locator("[data-qa='signup-button']");
    private final Locator signUpPasswordInput = page.locator("[data-qa='password']");
    private final Locator daySelect = page.locator("#days");
    private final Locator monthSelect = page.locator("#months");
    private final Locator yearSelect = page.locator("#years");
    private final Locator firstNameInput = page.locator("#first_name");
    private final Locator lastNameInput = page.locator("#last_name");
    private final Locator stateInput = page.locator("#state");
    private final Locator cityInput = page.locator("#city");
    private final Locator countrySelect = page.locator("#country");
    private final Locator zipCodeInput = page.locator("#zipcode");
    private final Locator mobileNumberInput = page.locator("#mobile_number");
    private final Locator createAccountButton = page.locator("button[data-qa='create-account']");
    private final Locator addressInput = page.locator("[data-qa='address']");
    private final Locator accountCreatedMessageLocator  = page.locator("[data-qa='account-created']");

    private ActionBot actionBot;

    public LoginPage(Page page) {
        super(page); // Call the constructor of BasePage
        this.actionBot = new ActionBot(page); // Instantiate the ActionBot
    }

    public void enterUsername(String username) {
        actionBot.fill(usernameInput, username);
    }

    public void enterPassword(String password) {
        actionBot.fill(passwordInput, password);
    }

    public void clickLoginButton() {
        actionBot.click(loginButton);
    }

    public void clickLogoutButton() {
        actionBot.click(logoutButton);
    }

    public String getErrorMessage() {
        return actionBot.getText(incorrectLoginMessage);
    }

    public void verifyErrorMessage(String actualLoginErrorText,String referenceText){
        Assert.assertEquals(actualLoginErrorText,referenceText);
    }

    public void signInUserNameInsert() {
        actionBot.fill(signInUserNameInput, "test_user" + generateRandomPassword(4));
    }

    public void signInEmailInsert(){
        actionBot.fill(signInEmailInput, generateRandomEmail());
    }

    public void clickSignButton(){
        actionBot.click(signUpButton);
    }

    public void selectRadioButton(String genderType){
        actionBot.selectRadioButton(page.locator(genderType));
    }

    public void signUpPasswordInsert (){
        actionBot.fill(signUpPasswordInput, generateRandomPassword(4));
    }

    public void dateOfBirthSelect (String day, String month, String year){
        actionBot.selectOptionByValue(daySelect, day);
        actionBot.selectOptionByText(monthSelect, month);
        actionBot.selectOptionByValue(yearSelect, year);
    }

    public void selectOptionalCheckboxes(){
        actionBot.selectCheckbox(page.locator("#newsletter"));
        actionBot.selectCheckbox(page.locator("#optin"));
    }

    public void firstNameInsert (){
        actionBot.fill(firstNameInput, "test_username" + generateRandomPassword(4));
    }

    public void lastNameInsert (){
        actionBot.fill(lastNameInput, "test_lastname" + generateRandomPassword(4));
    }

    public void addressInsert (){
        actionBot.fill(addressInput,"Bla Bla Bla");
    }

    public void scrollDownToElement(){
        actionBot.scrollToElement(cityInput);
    }

    public void selectCountry (String countryValue){
        actionBot.selectDropdownByLabel(countrySelect, countryValue);

    }

    public void stateInsert(String state){
        actionBot.fill(stateInput,state);
    }

    public void cityInsert(){
        actionBot.fill(cityInput,"Sderot");
    }

    public void zipcodeInsert (){
        actionBot.fill(zipCodeInput,"00000000");
    }

    public void mobileNumberInsert (){
        actionBot.fill(mobileNumberInput,"054000000");
    }

    public void createAccountClick (){
        actionBot.click(createAccountButton);
    }

    public String accountCreatedMessage(){
       var accountCreatedMessage = actionBot.getText(accountCreatedMessageLocator );
       return accountCreatedMessage;
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

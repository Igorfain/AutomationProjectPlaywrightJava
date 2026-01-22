package automationexercise.com.steps;
import automationexercise.com.pages.ContactUsPage;
import common.infra.ConsoleReporter;
import io.qameta.allure.Step;
import com.microsoft.playwright.Page;

public class ContactUsSteps {

    private final ContactUsPage contactUsPage;

    public ContactUsSteps(Page page) {
        this.contactUsPage = new ContactUsPage(page);
    }

    @Step("Click on 'Contact Us' button")
    public ContactUsSteps clickContactUsButton() {
        ConsoleReporter.log("Click on 'Contact Us' button");
        contactUsPage.clickContactUsButton();
        return this;
    }

    @Step("Verify 'Get In Touch' is existing in Contact Us page")
    public ContactUsSteps verifyGetInTouchText() {
        ConsoleReporter.log("Verify 'Get In Touch' is existing in Contact Us page");
        contactUsPage.verifyGetInTouchText();
        return this;
    }

    @Step("Fill the relevant fields ")
    public ContactUsSteps fillRelevantFields(String name,String email,String subject,String message) {
        ConsoleReporter.log("Fill the relevant fields");
        contactUsPage
                .insertName(name)
                .insertEmail(email)
                .insertSubject(subject)
                .insertMessage(message);
        return this;
    }

    @Step("Upload file in Contact Us form")
    public ContactUsSteps uploadFile(String filePath) {
        ConsoleReporter.log("Upload file in Contact Us form");
        contactUsPage.uploadFile(filePath);
        return this;
    }

    @Step("Click on 'Submit' button")
    public ContactUsSteps clickSubmitButton() {
        ConsoleReporter.log("Click on 'Submit' button");
        contactUsPage.clickSubmitButton();
        return this;
    }

    @Step("Verify success message is visible after form submission")
    public ContactUsSteps verifySuccessMessage() {
        ConsoleReporter.log("Verify success message is visible after form submission");
        contactUsPage.verifySuccessMessage();
        return this;
    }

    @Step("Click Home button after success message")
    public ContactUsSteps clickHomeButton() {
        ConsoleReporter.log("Click Home button after success message");
        contactUsPage.clickHomeButton();
        return this;
    }

    @Step("Verify navigation to Home page")
    public ContactUsSteps verifyNavigatedToHomePage() {
        ConsoleReporter.log("Verify navigation to Home page");
        contactUsPage.verifyNavigatedToHomePage();
        return this;
    }

}

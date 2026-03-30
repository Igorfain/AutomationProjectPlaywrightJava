package automationexercise.com.pages;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;
import automationexercise.com.infra.actions.ActionBot;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;
import java.util.regex.Pattern;

public class ContactUsPage extends BasePage{

    private final Locator contactUsButton;
    private final Locator getInTouchText;
    private final Locator nameInput;
    private final Locator emailInput;
    private final Locator subjectInput;
    private final Locator messageInput;
    private final Locator uploadFileInput;
    private final Locator submitButton;
    private final Locator successMessage;
    private final Locator homeButton;

    private ActionBot actionBot;

    public ContactUsPage(Page page) {
        super(page);
        this.actionBot = new ActionBot(page);
        this.contactUsButton = page.locator("a[href*='contact_us']");
        this.getInTouchText = page.locator("h2.title.text-center", new Page.LocatorOptions().setHasText("Get In Touch"));
        this.nameInput = page.locator("[data-qa='name']");
        this.emailInput = page.locator("[data-qa='email']");
        this.subjectInput = page.locator("[data-qa='subject']");
        this.messageInput = page.locator("[data-qa='message']");
        this.uploadFileInput = page.locator("input[name='upload_file']");
        this.submitButton = page.locator("input[type='submit']");
        this.successMessage = page.locator(".status.alert-success");
        this.homeButton = page.locator("a.btn-success[href='/']");
    }

    public void clickContactUsButton() {
        actionBot.click(contactUsButton);
    }

    public void verifyGetInTouchText() {
        assertThat(getInTouchText).hasText("Get In Touch");
    }

    public ContactUsPage insertName(String name) {
        actionBot.type(nameInput, name,50);
        return this;
    }

    public ContactUsPage insertEmail(String email) {
        actionBot.type(emailInput, email,50);
        return this;
    }

    public ContactUsPage insertSubject(String subject) {
        actionBot.type(subjectInput, subject,50);
        return this;
    }

    public ContactUsPage insertMessage(String message) {
        actionBot.type(messageInput, message,50);
        return this;
    }

    public ContactUsPage uploadFile(String filePath) {
        actionBot.setInputFiles(uploadFileInput, Paths.get(filePath));
        return this;
    }

    public ContactUsPage clickSubmitButton() {
        page.onceDialog(dialog -> dialog.accept());
        page.locator("input[type='submit']").click();
        return this;
    }

    public ContactUsPage verifySuccessMessage() {
        successMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        assertThat(successMessage).isVisible();
        assertThat(successMessage).hasText("Success! Your details have been submitted successfully.");
        return this;
    }

    public ContactUsPage clickHomeButton() {
        actionBot.click(homeButton);
        return this;
    }

    public ContactUsPage verifyNavigatedToHomePage() {

        assertThat(page).hasURL(Pattern.compile("https://automationexercise\\.com/.*"));
        assertThat(page.locator("div.carousel-inner").first()).isVisible();
        return this;
    }

}

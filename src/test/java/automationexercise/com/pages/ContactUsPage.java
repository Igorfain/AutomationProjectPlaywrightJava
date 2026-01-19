package automationexercise.com.pages;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;
import automationexercise.com.infra.actions.ActionBot;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;
import java.util.regex.Pattern;

public class ContactUsPage extends BasePage{

    private final Locator contactUsButton = page.locator("a[href*='contact_us']");
    private final Locator getInTouchText = page.locator("h2.title.text-center", new Page.LocatorOptions().setHasText("Get In Touch"));
    private final Locator nameInput = page.locator("[data-qa='name']");
    private final Locator emailInput = page.locator("[data-qa='email']");
    private final Locator subjectInput = page.locator("[data-qa='subject']");
    private final Locator messageInput = page.locator("[data-qa='message']");
    private final Locator uploadFileInput = page.locator("input[name='upload_file']");
    private final Locator submitButton = page.locator("input[type='submit']");
    private final Locator successMessage = page.locator(".status.alert-success");
    private final Locator homeButton = page.locator("a.btn-success[href='/']");


    private ActionBot actionBot;

    public ContactUsPage(Page page) {
        super(page); // Call the constructor of BasePage
        this.actionBot = new ActionBot(page); // Instantiate the ActionBot
    }

    public void clickContactUsButton() {
        actionBot.click(contactUsButton);
    }

    public void verifyGetInTouchText() {
        assertThat(getInTouchText).hasText("Get In Touch");
    }

    public ContactUsPage insertName(String name) {
        actionBot.fill(nameInput, name);
        return this;
    }

    public ContactUsPage insertEmail(String email) {
        actionBot.fill(emailInput, email);
        return this;
    }

    public ContactUsPage insertSubject(String subject) {
        actionBot.fill(subjectInput, subject);
        return this;
    }

    public ContactUsPage insertMessage(String message) {
        actionBot.fill(messageInput, message);
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

    public ContactUsPage acceptAlert() {
        page.onceDialog(dialog -> dialog.accept());
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

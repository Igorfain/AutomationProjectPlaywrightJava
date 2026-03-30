package automationexercise.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MainPage extends BasePage {

    private final Locator loggedInText;
    public final Locator logo;

    public MainPage(Page page) {
        super(page);
        this.loggedInText = page.locator("a i.fa.fa-user + b");
        this.logo = page.locator("img[alt='Website for automation practice']");
    }

    public String getLoggedInUserText() {
        return loggedInText.textContent().trim();
    }

    public void verifyLoggedInUser(String expectedUserText) {
        String loggedInUser = getLoggedInUserText();
        assert loggedInUser.contains(expectedUserText) : "Expected :'" + expectedUserText + "' in logged-in user text, but got: " + loggedInUser;
    }
}
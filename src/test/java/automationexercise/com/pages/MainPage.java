package automationexercise.com.pages;

import com.microsoft.playwright.Page;

public class MainPage extends BasePage {
    // Locators
    private String loggedInTextSelector = "a i.fa.fa-user + b";


    public MainPage(Page page) {
        super(page);
    }

    public String getLoggedInUserText() {
        // Ensure page and loggedInTextSelector are properly initialized
        String loggedInUser = page.locator(loggedInTextSelector).textContent().trim();
        return loggedInUser;
    }

    public void verifyLoggedInUser(String expectedUserText) {
        // Assert that the logged-in user text contains "TestUser"
        String loggedInUser = getLoggedInUserText();
        assert loggedInUser.contains(expectedUserText) : "Expected :" +"'"+ expectedUserText +"'"+ " in logged-in user text, but got: " + loggedInUser;
    }

}


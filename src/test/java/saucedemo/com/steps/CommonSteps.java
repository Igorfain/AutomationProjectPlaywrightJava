package saucedemo.com.steps;

import com.microsoft.playwright.Page;

public class CommonSteps {
    protected Page page;

    public CommonSteps(Page page) {
        this.page = page;
    }

//    // Common step to fill text in an input field
//    public void fillInputField(String locator, String value) {
//        Locator inputField = page.locator(locator);
//        inputField.fill(value);
//    }
//
//    // Common step to click a button
//    public void clickButton(String locator) {
//        Locator button = page.locator(locator);
//        button.click();
//    }
//
//    // Common step to verify the presence of text in an element
//    public void verifyTextInElement(String locator, String expectedText) {
//        Locator element = page.locator(locator);
//        String actualText = element.textContent();
//        if (!actualText.contains(expectedText)) {
//            throw new AssertionError("Expected text: " + expectedText + " not found in element: " + actualText);
//        }
//    }
//
//    // Common step to wait for an element to be visible
//    public void waitForElementVisible(String locator) {
//        Locator element = page.locator(locator);
//        element.waitFor();
//    }
}

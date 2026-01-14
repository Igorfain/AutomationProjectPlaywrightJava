package saucedemo.com.infra.actions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ActionBot {

    private Page page;

    public ActionBot(Page page) {
        this.page = page;
    }

    /**
     * Clicks on an element using its CSS selector.
     *
     * @param selector CSS selector of the element to click
     */
    public void click(String selector) {
        page.locator(selector).click();
    }

    /**
     * Fills an input field with the given text.
     *
     * @param selector CSS selector of the input field
     * @param text     Text to input
     */
    public void fill(String selector, String text) {
        page.locator(selector).fill(text);
    }

    /**
     * Selects an option from a dropdown using the visible text.
     *
     * @param selector   CSS selector of the dropdown
     * @param optionText Visible text of the option to select
     */
    public void selectOptionByText(String selector, String optionText) {
        page.locator(selector).selectOption(optionText);
    }

    /**
     * Waits for an element to be visible on the page.
     *
     * @param selector CSS selector of the element
     */
    public void waitForVisibility(String selector) {
        page.locator(selector).waitFor();
    }

    /**
     * Checks if an element is visible on the page.
     *
     * @param selector CSS selector of the element
     * @return true if the element is visible, false otherwise
     */
    public boolean isVisible(String selector) {
        return page.locator(selector).isVisible();
    }

    /**
     * Checks if an element with the specified name attribute is visible on the page.
     * @param nameAttribute The value of the name attribute for the element.
     * @return true if the element is visible, false otherwise.
     */
    public boolean isVisibleByName(String nameAttribute) {
        return page.locator("[name='" + nameAttribute + "']").isVisible();
    }

    /**
     * Clicks on an element using its role.
     *
     * @param role ARIA role of the element
     */
    public void clickByRole(AriaRole role) {
        page.getByRole(role).click();
    }

    /**
     * Types text into an input field and presses Enter.
     *
     * @param selector CSS selector of the input field
     * @param text     Text to type
     */
    public void typeAndPressEnter(String selector, String text) {
        page.locator(selector).fill(text);
        page.keyboard().press("Enter");
    }

    /**
     * Checks if an element contains the specified text.
     *
     * @param selector CSS selector of the element
     * @param text     Text to check for
     * @return true if the element contains the text, false otherwise
     */
    public boolean containsText(String selector, String text) {
        return page.locator(selector).textContent().contains(text);
    }

    /**
     * Gets the text content of an element.
     *
     * @param selector CSS selector of the element
     * @return Text content of the element
     */
    public String getText(String selector) {
        return page.locator(selector).textContent();
    }

    /**
     * Scrolls to an element using its CSS selector.
     *
     * @param selector CSS selector of the element to scroll to
     */
    public void scrollToElement(String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    /**
     * Retrieves text from an element located by name attribute.
     * @param nameAttribute The value of the name attribute for the element.
     * @return The text content of the element.
     */
    public String getTextByName(String nameAttribute) {
        return page.locator("[name='" + nameAttribute + "']").innerText();
    }
}

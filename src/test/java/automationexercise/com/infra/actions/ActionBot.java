package automationexercise.com.infra.actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.nio.file.Path;

public class ActionBot {
    private final Page page;

    public ActionBot(Page page) {
        this.page = page;
    }

    /**
     * Clicks on an element.
     *
     * @param locator Playwright Locator of the element to click
     */
    public void click(Locator locator) {
        locator.click();
    }

    /**
     * Fills an input field with the given text.
     *
     * @param locator Locator of the input field
     * @param text    Text to input
     */
    public void fill(Locator locator, String text) {
        locator.fill(text);
    }

    /**
     * Types text into an input field and presses Enter.
     *
     * @param locator Locator of the input field
     * @param text    Text to type
     */
    public void typeAndPressEnter(Locator locator, String text) {
        locator.fill(text);
        locator.press("Enter");
    }

    /**
     * Scrolls to an element.
     *
     * @param locator Locator of the element to scroll to
     */
    public void scrollToElement(Locator locator) {
        locator.scrollIntoViewIfNeeded();
    }

    /**
     * Waits for an element to be visible on the page.
     *
     * @param locator Locator of the element
     */
    public void waitForVisibility(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    /**
     * Checks if an element is visible on the page.
     *
     * @param locator Locator of the element
     * @return true if visible, false otherwise
     */
    public boolean isVisible(Locator locator) {
        return locator.isVisible();
    }

    /**
     * Gets the text content of an element.
     *
     * @param locator Locator of the element
     * @return Text content
     */
    public String getText(Locator locator) {
        return locator.textContent();
    }

    /**
     * Checks if an element contains the specified text.
     *
     * @param locator Locator of the element
     * @param text    Text to check for
     * @return true if contains text, false otherwise
     */
    public boolean containsText(Locator locator, String text) {
        String content = locator.textContent();
        return content != null && content.contains(text);
    }

    /**
     * Selects an option from a dropdown by visible text.
     *
     * @param select Locator of the dropdown
     * @param label  Visible text of the option
     */
    public void selectOptionByText(Locator select, String label) {
        select.selectOption(new SelectOption().setLabel(label));
    }

    /**
     * Selects an option from a dropdown by value.
     *
     * @param select Locator of the dropdown
     * @param value  Value attribute of the option
     */
    public void selectOptionByValue(Locator select, String value) {
        select.selectOption(value);
    }

    /**
     * Selects a radio button.
     *
     * @param locator Locator of the radio button
     */
    public void selectRadioButton(Locator locator) {
        locator.check();
    }

    /**
     * Selects (checks) a checkbox.
     *
     * @param locator Locator of the checkbox
     */
    public void selectCheckbox(Locator locator) {
        locator.check();
    }

    /**
     * Unselects (unchecks) a checkbox.
     *
     * @param locator Locator of the checkbox
     */
    public void unselectCheckbox(Locator locator) {
        locator.uncheck();
    }

    /**
     * Gets an element by name attribute.
     *
     * @param nameAttribute value of the name attribute
     * @return Locator of the element
     */
    public Locator byName(String nameAttribute) {
        return page.locator("[name='" + nameAttribute + "']");
    }

    /**
     * Clicks an element by its ARIA role.
     *
     * @param role ARIA role of the element
     */
    public void clickByRole(AriaRole role) {
        page.getByRole(role).click();
    }

    /**
     * Clicks an element by its ARIA role and visible name.
     *
     * @param role ARIA role
     * @param name Visible name
     */
    public void clickByRole(AriaRole role, String name) {
        page.getByRole(role, new Page.GetByRoleOptions().setName(name)).click();
    }

    /**
     * Selects an option from a dropdown by visible label.
     *
     * @param locator The Playwright Locator for the dropdown
     * @param label   Visible text of the option
     */
    public void selectDropdownByLabel(Locator locator, String label) {
        locator.selectOption(new com.microsoft.playwright.options.SelectOption().setLabel(label));
    }

    /**
     * Selects an option from a dropdown by value.
     *
     * @param locator The Playwright Locator for the dropdown
     * @param value   Value attribute of the option
     */
    public void selectDropdownByValue(Locator locator, String value) {
        locator.selectOption(value);
    }

    /**
     * Uploads a file to the given input element.
     *
     * @param locator Playwright Locator of the file input element
     * @param filePath Path to the file to upload
     */
    public void setInputFiles(Locator locator, Path filePath) {
        locator.setInputFiles(filePath);
    }


}

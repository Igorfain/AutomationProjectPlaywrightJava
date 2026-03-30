package saucedemo.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage extends BasePage {

    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator postalCodeInput;
    private final Locator continueButtonLocator;

    public CheckoutPage(Page page) {
        super(page);
        this.firstNameInput = page.locator("[data-test='firstName']");
        this.lastNameInput = page.locator("[data-test='lastName']");
        this.postalCodeInput = page.locator("[data-test='postalCode']");
        this.continueButtonLocator = page.locator("[data-test='continue']");
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        firstNameInput.fill(firstName);
        lastNameInput.fill(lastName);
        postalCodeInput.fill(postalCode);
    }

    public void clickContinueButton() {
        continueButtonLocator.click();
    }
}

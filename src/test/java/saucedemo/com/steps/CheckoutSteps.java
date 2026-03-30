package saucedemo.com.steps;

import com.microsoft.playwright.Page;
import common.infra.ConsoleReporter;
import io.qameta.allure.Step;
import saucedemo.com.pages.CheckoutPage;

public class CheckoutSteps {

    private final CheckoutPage checkoutPage;

    public CheckoutSteps(Page page) {
        this.checkoutPage = new CheckoutPage(page);
    }

    @Step("Fill checkout form")
    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        ConsoleReporter.log("Fill checkout form");
        checkoutPage.fillCheckoutForm(firstName, lastName, postalCode);
    }

    @Step("Click Continue button")
    public void clickContinueButton() {
        ConsoleReporter.log("Click Continue button");
        checkoutPage.clickContinueButton();
    }
}

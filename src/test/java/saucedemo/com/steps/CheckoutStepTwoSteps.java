package saucedemo.com.steps;

import com.microsoft.playwright.Page;
import common.infra.ConsoleReporter;
import io.qameta.allure.Step;
import saucedemo.com.pages.CheckoutStepTwoPage;

public class CheckoutStepTwoSteps {

    private final CheckoutStepTwoPage checkoutStepTwoPage;

    public CheckoutStepTwoSteps(Page page) {
        this.checkoutStepTwoPage = new CheckoutStepTwoPage(page);
    }

    @Step("Click Finish on Checkout Step Two")
    public void clickFinish() {
        ConsoleReporter.log("Click Finish on Checkout Step Two");
        checkoutStepTwoPage.clickFinish();
    }

    @Step("Verify overview on Checkout Step Two")
    public void verifyOverview(String expectedName,
                               double expectedPrice,
                               String expectedPaymentInfo,
                               String expectedShippingInfo,
                               String expectedSubtotalLabel,
                               String expectedTaxLabel,
                               String expectedTotalLabel) {
        ConsoleReporter.log("Verify overview on Checkout Step Two");

        // Item name + price
        org.testng.Assert.assertEquals(checkoutStepTwoPage.getItemName(), expectedName,
                "Item name on checkout step two should match expected");
        org.testng.Assert.assertEquals(checkoutStepTwoPage.getItemPrice(), expectedPrice,
                "Item price on checkout step two should match expected");

        // Payment + Shipping
        org.testng.Assert.assertEquals(checkoutStepTwoPage.getPaymentInfo(), expectedPaymentInfo,
                "Payment Information should match expected");
        org.testng.Assert.assertEquals(checkoutStepTwoPage.getShippingInfo(), expectedShippingInfo,
                "Shipping Information should match expected");

        // Price Total block
        org.testng.Assert.assertEquals(checkoutStepTwoPage.getSubtotalLabel(), expectedSubtotalLabel,
                "Subtotal label should match expected");
        org.testng.Assert.assertEquals(checkoutStepTwoPage.getTaxLabel(), expectedTaxLabel,
                "Tax label should match expected");
        org.testng.Assert.assertEquals(checkoutStepTwoPage.getTotalLabel(), expectedTotalLabel,
                "Total label should match expected");
    }
}

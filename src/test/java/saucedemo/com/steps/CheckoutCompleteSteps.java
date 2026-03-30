package saucedemo.com.steps;

import com.microsoft.playwright.Page;
import common.infra.ConsoleReporter;
import io.qameta.allure.Step;
import saucedemo.com.pages.CheckoutCompletePage;

public class CheckoutCompleteSteps {

    private final CheckoutCompletePage checkoutCompletePage;

    public CheckoutCompleteSteps(Page page) {
        this.checkoutCompletePage = new CheckoutCompletePage(page);
    }

    @Step("Verify Checkout Complete page")
    public void verifyCheckoutCompletePage() {
        ConsoleReporter.log("Verify Checkout Complete page");
        org.testng.Assert.assertEquals(
                checkoutCompletePage.getPageTitleText(),
                "Checkout: Complete!",
                "Checkout complete page title should match expected"
        );

        org.testng.Assert.assertEquals(
                checkoutCompletePage.getCompleteHeaderText(),
                "Thank you for your order!",
                "Checkout complete header should match expected"
        );

        org.testng.Assert.assertEquals(
                checkoutCompletePage.getCompleteBodyText(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Checkout complete body text should match expected"
        );

        org.testng.Assert.assertTrue(checkoutCompletePage.isBackHomeVisible(),
                "Back Home button should be visible");
    }

    @Step("Click Back Home on Checkout Complete page")
    public void clickBackHome() {
        ConsoleReporter.log("Click Back Home on Checkout Complete page");
        checkoutCompletePage.clickBackHome();
    }
}

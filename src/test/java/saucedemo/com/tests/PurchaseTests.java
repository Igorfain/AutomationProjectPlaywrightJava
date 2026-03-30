package saucedemo.com.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.com.infra.base.BaseTest;
import saucedemo.com.steps.CartPageSteps;
import saucedemo.com.steps.CheckoutCompleteSteps;
import saucedemo.com.steps.CheckoutSteps;
import saucedemo.com.steps.CheckoutStepTwoSteps;
import saucedemo.com.steps.ProductPageSteps;

public class PurchaseTests extends BaseTest {

    private CartPageSteps cartPageSteps;
    private ProductPageSteps productPageSteps;
    private CheckoutSteps checkoutSteps;
    private CheckoutStepTwoSteps checkoutStepTwoSteps;
    private CheckoutCompleteSteps checkoutCompleteSteps;

    private static final String ITEM_NAME = "Sauce Labs Bike Light";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String ZIP_CODE = "12345";
    private static final String CARD_NAME = "SauceCard #31337";
    private static final String DELIVERY_TYPE = "Free Pony Express Delivery!";
    private static final String TAX = "$0.80";
    private static final String ITEM_TOTAL_PREFIX = "Item total: $";
    private static final String TAX_PREFIX = "Tax: ";
    private static final String TOTAL_PREFIX = "Total: $";
    private static final String TOTAL = "$10.79";

    @BeforeMethod
    public void setUpTest() {
        productPageSteps = new ProductPageSteps(page);
        cartPageSteps = new CartPageSteps(page);
        checkoutSteps = new CheckoutSteps(page);
        checkoutStepTwoSteps = new CheckoutStepTwoSteps(page);
        checkoutCompleteSteps = new CheckoutCompleteSteps(page);
    }

    @Test(description = "Purchase item E2E")
    public void purchaseItemE2E() {
        productPageSteps.openItemPage(ITEM_NAME);
        String expectedName = productPageSteps.getItemDetailsNameTextStep();
        double expectedPrice = productPageSteps.getItemDetailsPriceDoubleStep();
        productPageSteps.addItemToTheCart();
        productPageSteps.verifyRemoveButtonIsDisplaying();
        productPageSteps.ClickCartIcon();

        cartPageSteps.verifyCartItemDetails(expectedName, expectedPrice);
        cartPageSteps.clickCheckoutButton();
        checkoutSteps.fillCheckoutForm(FIRST_NAME, LAST_NAME, ZIP_CODE);
        checkoutSteps.clickContinueButton();

        checkoutStepTwoSteps.verifyOverview(
                expectedName,
                expectedPrice,
                CARD_NAME,
                DELIVERY_TYPE,
                ITEM_TOTAL_PREFIX + String.format(java.util.Locale.US, "%.2f", expectedPrice),
                TAX_PREFIX + TAX,
                TOTAL_PREFIX + TOTAL.substring(1)
        );

        checkoutStepTwoSteps.clickFinish();
        checkoutCompleteSteps.verifyCheckoutCompletePage();
        checkoutCompleteSteps.clickBackHome();
        productPageSteps.verifyInventoryPageIsDisplayed();

    }
}
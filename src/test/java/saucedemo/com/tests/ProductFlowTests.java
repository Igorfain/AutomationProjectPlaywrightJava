package saucedemo.com.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.com.infra.ConsoleReporter;
import saucedemo.com.infra.base.BaseTest;
import saucedemo.com.steps.CartPageSteps;
import saucedemo.com.steps.ProductPageSteps;

public class ProductFlowTests extends BaseTest {

    private CartPageSteps cartPageSteps;
    private ProductPageSteps productPageSteps;

    @BeforeMethod
    public void setUpTest() {
        productPageSteps = new ProductPageSteps(page);
        cartPageSteps = new CartPageSteps(page);
    }


    @Test(description = "Verify product page logo")
    public void testProductPageLogo() {
        ConsoleReporter.log("Step 1 - Verify logo");
        String logoText = productPageSteps.getLogoText();
        System.out.println("Retrieved logo text: " + logoText);
        assert logoText.equals("Swag Labs") : "Logo text does not match. Expected 'Swag Labs' but found '" + logoText + "'";
        ConsoleReporter.log("Test completed successfully");
    }

    @Test(description = "Open item page and add to cart")
    public void selectItemAndAddToCard() {
        ConsoleReporter.log("Step 1 - Open item page");
        var itemName1 = "Sauce Labs Backpack";
        productPageSteps.openItemPage(itemName1);
        ConsoleReporter.log("Step 2 - Open item to the cart");
        productPageSteps.addItemToTheCart();
        ConsoleReporter.log("Step 3 - Verify Remove button is displaying");
        productPageSteps.verifyRemoveButtonIsDisplaying();
        ConsoleReporter.log("Test completed successfully");
    }

    @Test(description = "Open item page and add to cart")
    public void openCartAndRemoveOrder() {
        ConsoleReporter.log("Step 1 - Open item page");
        var itemName1 = "Sauce Labs Backpack";
        productPageSteps.openItemPage(itemName1);
        ConsoleReporter.log("Step 2 - Open item to the cart");
        productPageSteps.addItemToTheCart();
        ConsoleReporter.log("Step 3 - Click the Cart icon and open cart page");
        productPageSteps.ClickCartIcon();
        ConsoleReporter.log("Step 4 - Remove order from the cart");
        cartPageSteps.removeOrderFromCart();
        ConsoleReporter.log("Step 5 - Verify order removed from the cart");
        cartPageSteps.verifyOrderRemovedFromCart();
    }

    @Test(description = "Verify Continue shopping button present and text ")
    public void verifyShoppingButtonIsExisting() {
        String buttonNameRef = "Continue Shopping";
        ConsoleReporter.log("Step 1 - Open Cart page");
        productPageSteps.ClickCartIcon();
        ConsoleReporter.log("Step 2 - Verify Button is existing");
        cartPageSteps.verifyContinueShopButtonIsExisting();
        ConsoleReporter.log("Step 3 - Verify Button text");
        cartPageSteps.verifyButtonName(buttonNameRef);
        ConsoleReporter.log("Step 4 - Verify Button is working and main page is displayed");
        cartPageSteps.clickButtonAndVerifyMainPageIsDisplayed();
    }

    @Test(description = "Logout from the site")
    public void logoutFromSite() {
        ConsoleReporter.log("Step 1 - Logout from the site");
        productPageSteps.logoutFromSite();
        ConsoleReporter.log("Step 2 - Verify user is logged out");
        productPageSteps.verifyLogout();
    }

}

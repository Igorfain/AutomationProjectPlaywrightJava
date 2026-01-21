package saucedemo.com.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
        String logoText = productPageSteps.getLogoText();
        System.out.println("Retrieved logo text: " + logoText);
        assert logoText.equals("Swag Labs") : "Logo text does not match. Expected 'Swag Labs' but found '" + logoText + "'";
    }

    @Test(description = "Open item page and add to cart")
    public void selectItemAndAddToCard() {
        var itemName1 = "Sauce Labs Backpack";
        productPageSteps.openItemPage(itemName1);
        productPageSteps.addItemToTheCart();
        productPageSteps.verifyRemoveButtonIsDisplaying();
    }

    @Test(description = "Open item page and add to cart")
    public void openCartAndRemoveOrder() {
        var itemName1 = "Sauce Labs Backpack";
        productPageSteps.openItemPage(itemName1);
        productPageSteps.addItemToTheCart();
        productPageSteps.ClickCartIcon();
        cartPageSteps.removeOrderFromCart();
        cartPageSteps.verifyOrderRemovedFromCart();
    }

    @Test(description = "Verify Continue shopping button present and text ")
    public void verifyShoppingButtonIsExisting() {
        String buttonNameRef = "Continue Shopping";
        productPageSteps.ClickCartIcon();
        cartPageSteps.verifyContinueShopButtonIsExisting();
        cartPageSteps.verifyButtonName(buttonNameRef);
        cartPageSteps.clickButtonAndVerifyMainPageIsDisplayed();
    }

    @Test(description = "Logout from the site")
    public void logoutFromSite() {
        productPageSteps.logoutFromSite();
        productPageSteps.verifyLogout();
    }

}

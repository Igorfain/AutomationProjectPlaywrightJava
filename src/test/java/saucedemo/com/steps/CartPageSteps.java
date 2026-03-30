package saucedemo.com.steps;

import com.microsoft.playwright.Page;
import common.infra.ConsoleReporter;
import io.qameta.allure.Step;
import saucedemo.com.pages.CartPage;

public class CartPageSteps {

    private final CartPage cartPage;

    public CartPageSteps(Page page) {
        this.cartPage = new CartPage(page);
    }

    @Step("Remove order from the cart")
    public void removeOrderFromCart() {
        ConsoleReporter.log("Removing order from the cart");
        cartPage.checkItemInCartAndRemove();
    }

    @Step("Verify order removed from the cart")
    public void verifyOrderRemovedFromCart() {
        ConsoleReporter.log("Verifying order is removed from the cart");
        cartPage.verifyItemRemovedFromCart();
    }

    @Step("Verify Continue Shop Button is existing")
    public void verifyContinueShopButtonIsExisting(){
        ConsoleReporter.log("Verifying Continue Shop Button is existing");
        cartPage.verifyContinueShopButtonIsExisting();
    }

    @Step("Verify button name")
    public void verifyButtonName(String buttonNameRef){
        ConsoleReporter.log("Verifying button name");
        cartPage.verifyButtonName(buttonNameRef);
    }

    @Step("Click Continue Shopping and verify main page is displayed")
    public void clickButtonAndVerifyMainPageIsDisplayed(){
        ConsoleReporter.log("Clicking button and verifying main page is displayed");
        cartPage.clickButtonAndVerifyMainPageIsDisplayed();
    }

    @Step("Get item name from cart")
    public String getCartItemNameStep() {
        ConsoleReporter.log("Get item name from cart");
        return cartPage.getCartItemName();
    }

    @Step("Get item price from cart as double")
    public double getCartItemPriceStep() {
        ConsoleReporter.log("Get item price from cart as double");
        return cartPage.getCartItemPrice();
    }

    @Step("Click Checkout button")
    public void clickCheckoutButton() {
        ConsoleReporter.log("Click Checkout button");
        cartPage.clickCheckoutButton();
    }

    @Step("Verify cart item details - name: {expectedName}, price: {expectedPrice}")
    public void verifyCartItemDetails(String expectedName, double expectedPrice) {
        ConsoleReporter.log("Verify cart item details - name: " + expectedName + ", price: " + expectedPrice);
        String actualName = getCartItemNameStep();
        double actualPrice = getCartItemPriceStep();
        org.testng.Assert.assertEquals(actualName, expectedName, "Item name in cart should match details page");
        org.testng.Assert.assertEquals(actualPrice, expectedPrice, "Item price in cart should match details page");
    }
}

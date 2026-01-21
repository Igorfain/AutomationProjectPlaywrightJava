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

    @Step("")
    public void clickButtonAndVerifyMainPageIsDisplayed(){
        ConsoleReporter.log("Clicking button and verifying main page is displayed");
        cartPage.clickButtonAndVerifyMainPageIsDisplayed();
    }
}

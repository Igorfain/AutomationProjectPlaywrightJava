package saucedemo.com.steps;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import saucedemo.com.pages.CartPage;

public class CartPageSteps {

    private final CartPage cartPage;

    public CartPageSteps(Page page) {
        this.cartPage = new CartPage(page);  // Corrected the typo
    }

    @Step("Remove order from the cart")
    public void removeOrderFromCart() {
        cartPage.checkItemInCartAndRemove();
    }

    @Step("Verify order removed from the cart")
    public void verifyOrderRemovedFromCart() {
        cartPage.verifyItemRemovedFromCart();
    }

    @Step("Verify Continue Shop Button is existing")
    public void verifyContinueShopButtonIsExisting(){
        cartPage.verifyContinueShopButtonIsExisting();
    }

    @Step("Verify button name")
    public void verifyButtonName(String buttonNameRef){
        cartPage.verifyButtonName(buttonNameRef);
    }

    @Step("")
    public void clickButtonAndVerifyMainPageIsDisplayed(){
        cartPage.clickButtonAndVerifyMainPageIsDisplayed();
    }
}

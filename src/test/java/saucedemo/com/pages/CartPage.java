package saucedemo.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import saucedemo.com.infra.actions.ActionBot;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class CartPage extends BasePage {

    private final Locator removeOrderButton;
    private final Locator inventoryInCart;
    private final Locator continueShoppingButton;
    private final Locator cartItemName;
    private final Locator cartItemPrice;
    private final Locator checkoutButton;
    private final Locator addToCartButton;

    public CartPage(Page page) {
        super(page);
        this.removeOrderButton = page.locator("[data-test='remove-sauce-labs-backpack']");
        this.inventoryInCart = page.locator("[data-test='inventory-item-name']");
        this.continueShoppingButton = page.locator("[name='continue-shopping']");
        this.cartItemName = page.locator(".cart_item .inventory_item_name");
        this.cartItemPrice = page.locator(".cart_item .inventory_item_price");
        this.checkoutButton = page.locator("[data-test='checkout']");
        this.addToCartButton = page.locator("[name='add-to-cart-sauce-labs-backpack']");
    }

    public void checkItemInCartAndRemove() {
        var isItemVisible = inventoryInCart.nth(0).isVisible();
        if (isItemVisible) {
            removeItemFromCart();
        } else {
            System.out.println("No item in the cart list");
            Assert.fail("Test failed intentionally: No item found in the cart.");
        }

    }

    public void removeItemFromCart() {
        removeOrderButton.nth(0).click();

    }

    public void verifyItemRemovedFromCart() {
        assertTrue(inventoryInCart.nth(0).isHidden(),
                "Cart item should be hidden after removal");
    }

    public void verifyContinueShopButtonIsExisting() {
        assertTrue(continueShoppingButton.isVisible(), "Continue shopping button should be visible");
    }

    public void verifyButtonName(String buttonNameRef) {
        String buttonText = continueShoppingButton.innerText();
        assertNotNull(buttonText, "The button with name '" + buttonNameRef + "' should exist and have text.");
        System.out.println("Button text: " + buttonText);
    }

    public void clickButtonAndVerifyMainPageIsDisplayed() {
        continueShoppingButton.click();
        boolean isMainPageDisplayed = addToCartButton.isVisible();
        assertTrue(isMainPageDisplayed, "The main page should be displayed after clicking the 'continue-shopping' button.");
    }

    public String getCartItemName() {
        return cartItemName.innerText();
    }

    public double getCartItemPrice() {
        String priceText = cartItemPrice.innerText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }
}

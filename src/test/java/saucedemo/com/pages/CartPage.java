package saucedemo.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class CartPage extends BasePage {

    // Generic, reusable selectors
    private final Locator cartItem;
    private final Locator cartItemName;
    private final Locator cartItemPrice;
    private final Locator continueShoppingButton;
    private final Locator checkoutButton;

    public CartPage(Page page) {
        super(page);
        this.cartItem = page.locator(".cart_item");
        this.cartItemName = page.locator(".cart_item .inventory_item_name");
        this.cartItemPrice = page.locator(".cart_item .inventory_item_price");
        this.continueShoppingButton = page.locator("[name='continue-shopping']");
        this.checkoutButton = page.locator("[data-test='checkout']");
    }

    public void checkItemInCartAndRemove() {
        if (cartItem.nth(0).isVisible()) {
            removeItemFromCart(0);
        } else {
            System.out.println("No item in the cart list");
            Assert.fail("Test failed intentionally: No item found in the cart.");
        }
    }

    public void removeItemFromCart(int itemIndex) {
        page.locator(".cart_item").nth(itemIndex).locator("button[data-test*='remove']").click();
    }

    public void removeItemFromCartByName(String itemName) {
        page.locator(String.format(".cart_item:has(:text(\"%s\")) button[data-test*='remove']", itemName)).click();
    }

    public void verifyItemRemovedFromCart() {
        assertTrue(cartItem.nth(0).isHidden(),
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
        boolean isMainPageDisplayed = page.locator("button[data-test*='add-to-cart']").first().isVisible();
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

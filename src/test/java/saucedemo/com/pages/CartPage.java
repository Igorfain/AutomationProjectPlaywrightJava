package saucedemo.com.pages;

import com.microsoft.playwright.Page;
import org.testng.Assert;
import saucedemo.com.infra.actions.ActionBot;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class CartPage extends BasePage {

    private String removeOrderButton = "[data-test='remove-sauce-labs-backpack']";
    private String inventoryInCart = "[data-test='inventory-item-name']";

    ActionBot actionBot = new ActionBot(page);

    public CartPage(Page page) {
        super(page); // Call the constructor of BasePage
    }

    public void removeOrder() {
        page.locator(removeOrderButton).isVisible();
        page.locator(removeOrderButton);
    }

    public void checkItemInCartAndRemove() {
        var isItemVisible = page.locator(inventoryInCart).nth(0).isVisible();
        if (isItemVisible) {
            removeItemFromCart();
        } else {
            System.out.println("No item in the cart list");
            Assert.fail("Test failed intentionally: No item found in the cart.");
        }

    }

    public void removeItemFromCart() {
        page.locator(removeOrderButton).nth(0).click();

    }

    public void verifyItemRemovedFromCart() {
        page.locator(inventoryInCart).nth(0).isHidden();
    }

    public void verifyContinueShopButtonIsExisting() {
        actionBot.isVisibleByName("continue-shopping");
    }

    public void verifyButtonName(String buttonNameRef) {
        String buttonText = actionBot.getText("[name='continue-shopping']");
        assertNotNull(buttonText, "The button with name '" + buttonNameRef + "' should exist and have text.");
        System.out.println("Button text: " + buttonText);
    }

    public void clickButtonAndVerifyMainPageIsDisplayed() {
        page.locator("[name='continue-shopping']").click();
        boolean isMainPageDisplayed = page.locator("[name='add-to-cart-sauce-labs-backpack']").isVisible();
        assertTrue(isMainPageDisplayed, "The main page should be displayed after clicking the 'continue-shopping' button.");
    }
}

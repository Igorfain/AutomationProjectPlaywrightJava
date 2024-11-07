package saucedemo.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductPage extends BasePage {

    private Locator pageTitle = page.locator(".app_logo");
    private Locator addToCartButton = page.locator("#add-to-cart");
    private Locator removeItemButton = page.locator("#remove");
    private Locator cartIcon = page.locator("[data-test='shopping-cart-link']");

    public ProductPage(Page page) {
        super(page); // Call the constructor of BasePage
    }

    public String getProductLogoText() {
        String pageTitleText = pageTitle.innerText();
        return pageTitleText;
    }

    public void openItemPage(String itemName) {
        String inventoryItemSelector = String.format(".inventory_item_name:text('%s')", itemName);
        page.locator(inventoryItemSelector).click();
    }

    public void addItemToCart() {
        addToCartButton.click();
    }

    public void removeItemFromCartButtonExist() {
      var removeItemButtonText =  removeItemButton.innerText();
      assert removeItemButtonText.equals("Remove");
    }

    public void clickCartIcon() {
        cartIcon.click();
    }
}

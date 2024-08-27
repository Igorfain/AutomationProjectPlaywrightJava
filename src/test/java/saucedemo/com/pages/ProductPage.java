package saucedemo.com.pages;

import com.microsoft.playwright.Page;

public class ProductPage extends BasePage {

    private String usernameSelector = "div.app_logo";

    public ProductPage(Page page) {
        super(page); // Call the constructor of BasePage
    }

    public String getProductLogoText() {
        String logoText = page.locator(".app_logo").innerText();
        return logoText;
    }

    public void openItemPage(String itemName) {
        String selector = String.format(".inventory_item_name:text('%s')", itemName);
        page.locator(selector).click();
    }

    public void addItemToCart() {
        page.locator("#add-to-cart").click();
    }

    public void removeItemFromCartButtonExist() {
      var removeItemButton =  page.locator("#remove").innerText();
      assert removeItemButton.equals("Remove");
    }
}

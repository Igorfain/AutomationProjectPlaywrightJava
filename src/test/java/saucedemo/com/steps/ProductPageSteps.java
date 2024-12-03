package saucedemo.com.steps;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import saucedemo.com.pages.ProductPage;

public class ProductPageSteps {

    private final ProductPage productPage;

    public ProductPageSteps(Page page) {
        this.productPage = new ProductPage(page);
    }

    @Step("Get the logo from the product page")
    public String getLogoText() {
        return productPage.getProductLogoText();
    }

    @Step("Open item page : {itemName} ")
    public void openItemPage(String itemName) {
        productPage.openItemPage(itemName);
    }

    @Step("Click Add to cart ")
    public void addItemToTheCart() {
        productPage.addItemToCart();
    }

    @Step("Verify Remove Button is displaying ")

    public void verifyRemoveButtonIsDisplaying() {
        productPage.removeItemFromCartButtonExist();
    }

    @Step("Click the Cart Icon")
    public void ClickCartIcon() {
        productPage.clickCartIcon();
    }

    @Step("Click the sorting dropdown and select value")
    public void ClickTheSortingDropdownAndSelectValue(String sortingOption) {
        productPage.clickSortingDropdownAndSelectValue(sortingOption);
        productPage.verifyItemsOrderedByAtoZ();
    }


}

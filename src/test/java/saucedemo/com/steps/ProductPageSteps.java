package saucedemo.com.steps;

import com.microsoft.playwright.Page;
import common.infra.ConsoleReporter;
import io.qameta.allure.Step;
import saucedemo.com.pages.ProductPage;

public class ProductPageSteps {

    private final ProductPage productPage;

    public ProductPageSteps(Page page) {
        this.productPage = new ProductPage(page);
    }

    @Step("Get the logo from the product page")
    public String getLogoText() {
        ConsoleReporter.log("Get the logo from the product page");
        return productPage.getProductLogoText();
    }

    @Step("Open item page : {itemName} ")
    public void openItemPage(String itemName) {
        ConsoleReporter.log("Open item page : " + itemName);
        productPage.openItemPage(itemName);
    }

    @Step("Click Add to cart ")
    public void addItemToTheCart()
    {
        ConsoleReporter.log("Click Add to cart ");
        productPage.addItemToCart();
    }

    @Step("Verify Remove Button is displaying ")
    public void verifyRemoveButtonIsDisplaying() {
        ConsoleReporter.log("Verify Remove Button is displaying ");
        productPage.removeItemFromCartButtonExist();
    }

    @Step("Click the Cart Icon")
    public void ClickCartIcon() {
        ConsoleReporter.log("Click the Cart Icon");
        productPage.clickCartIcon();
    }

    @Step("Click the sorting dropdown and select value")
    public void ClickTheSortingDropdownAndSelectValue(String sortingOption) {
        ConsoleReporter.log("Click the sorting dropdown and select value: " + sortingOption);
        productPage.clickSortingDropdownAndSelectValue(sortingOption);
    }

    @Step("Verify items was ordered A to Z")
    public void verifyItemsOrderedAtoZ() {
        ConsoleReporter.log("Verify items was ordered A to Z");
        productPage.verifyItemsOrderedByAtoZ();
    }

    @Step("Verify items was ordered A to Z")
    public void verifyItemsPriceOrderedLowHigh() {
        ConsoleReporter.log("Verify items was ordered Price Low to High");
        productPage.verifyItemsPriceOrderedLowHigh();
    }

    @ Step("Logout from the site")
    public void logoutFromSite() {
        ConsoleReporter.log("Logout from the site");
        productPage.logout();
    }

    @Step("Verify Logout is successful")
    public void verifyLogout() {
        ConsoleReporter.log("Verify Logout is successful");
        productPage.verifyLogout();
    }

}

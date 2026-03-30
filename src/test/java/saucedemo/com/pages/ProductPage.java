package saucedemo.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    private final Locator pageTitleLocator;
    private final Locator addToCartButtonLocator;
    private final Locator removeItemButtonLocator;
    private final Locator cartIconLocator;
    private final Locator sortingDropdownLocator;
    private final Locator inventoryItemNameLocator;
    private final Locator priceElementLocator;
    private final Locator itemDetailsPriceLocator;
    private final Locator burgerMenuLocator;
    private final Locator logoutSidebarButtonLocator;

    public ProductPage(Page page) {
        super(page);
        this.pageTitleLocator = page.locator(".app_logo");
        this.addToCartButtonLocator = page.locator("#add-to-cart");
        this.removeItemButtonLocator = page.locator("#remove");
        this.cartIconLocator = page.locator("[data-test='shopping-cart-link']");
        this.sortingDropdownLocator = page.locator(".product_sort_container");
        this.inventoryItemNameLocator = page.locator(".inventory_item_name");
        this.priceElementLocator = page.locator(".inventory_item_price");
        this.itemDetailsPriceLocator = page.locator(".inventory_details_price");
        this.burgerMenuLocator = page.locator("#react-burger-menu-btn");
        this.logoutSidebarButtonLocator = page.locator("#logout_sidebar_link");
    }

    public double getItemDetailsPriceDouble() {
        String priceText = itemDetailsPriceLocator.innerText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    public String getProductLogoText() {
        return pageTitleLocator.innerText();
    }

    public void openItemPage(String itemName) {
        String inventoryItemSelector = String.format(".inventory_item_name:text('%s')", itemName);
        page.locator(inventoryItemSelector).click();
    }

    public void addItemToCart() {
        addToCartButtonLocator.click();
    }

    public void removeItemFromCartButtonExist() {
        var removeItemButtonText = removeItemButtonLocator.innerText();
        assert removeItemButtonText.equals("Remove");
    }

    public void clickCartIcon() {
        cartIconLocator.click();
    }

    public void clickSortingDropdownAndSelectValue(String sortingOption) {
        sortingDropdownLocator.click();
        sortingDropdownLocator.selectOption(new SelectOption().setValue(sortingOption));

    }

    public void verifyItemsOrderedByAtoZ() {
        page.waitForTimeout(2000);
        List<String> itemNames = new ArrayList<>(inventoryItemNameLocator
                .allTextContents());

        if (isSortedZToA(itemNames)) {
            System.out.println("The items are sorted correctly by Z to A.");
        } else {
            System.out.println("The items are NOT sorted by Z to A.");
        }
    }

    // Helper method to check if the list is sorted in descending order (Z to A)
    private static boolean isSortedZToA(List<String> list) {
        List<String> sortedList = list.stream()
                .sorted(Comparator.reverseOrder()) // Sort in reverse (Z to A)
                .toList();
        return list.equals(sortedList); // Check if the list matches the sorted list
    }


    public void verifyItemsPriceOrderedLowHigh(){
        // Locate all price elements
        List<String> priceTexts = priceElementLocator.allInnerTexts();
        // Parse the prices into a list of doubles
        List<Double> prices = priceTexts.stream()
                .map(price -> Double.parseDouble(price.replace("$", "").trim()))
                .collect(Collectors.toList());

        // Check if the list is sorted
        boolean isSorted = isSortedLowToHigh(prices);
  System.out.println("Are the prices sorted from low to high? " + isSorted);

    }

    // Helper method to check if the list is sorted
    private static boolean isSortedLowToHigh(List<Double> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public void logout() {
        burgerMenuLocator.click();
        logoutSidebarButtonLocator.click();
    }

    public void verifyLogout() {
        var loginButton = page.locator("#login-button");
        assert loginButton.isVisible();
    }

    public String getItemDetailsNameText() {
        return page.locator(".inventory_details_name").innerText();
    }

    public boolean isInventoryPageDisplayed() {
        return page.locator(".inventory_list").isVisible();
    }
}

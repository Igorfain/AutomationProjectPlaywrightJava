package saucedemo.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    private final Locator pageTitleLocator = page.locator(".app_logo");
    private final Locator addToCartButtonLocator = page.locator("#add-to-cart");
    private final Locator removeItemButtonLocator = page.locator("#remove");
    private final Locator cartIconLocator = page.locator("[data-test='shopping-cart-link']");
    private final Locator sortingDropdownLocator = page.locator(".product_sort_container");
    private final Locator inventoryItemNameLocator = page.locator(".inventory_item_name");
    private final Locator priceElementLocator = page.locator(".inventory_item_price");
    private final Locator burgerMenuLocator = page.locator("#react-burger-menu-btn");
    private final Locator logoutSidebarButtonLocator = page.locator("#logout_sidebar_link");


    public ProductPage(Page page) {
        super(page); // Call the constructor of BasePage
    }

    public String getProductLogoText() {
        String pageTitleText = pageTitleLocator.innerText();
        return pageTitleText;
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
}

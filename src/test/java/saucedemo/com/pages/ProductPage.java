package saucedemo.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductPage extends BasePage {

    private Locator pageTitle = page.locator(".app_logo");
    private Locator addToCartButton = page.locator("#add-to-cart");
    private Locator removeItemButton = page.locator("#remove");
    private Locator cartIcon = page.locator("[data-test='shopping-cart-link']");
    private Locator sortingDropdown = page.locator(".product_sort_container");
    private Locator inventoryItemName = page.locator(".inventory_item_name");


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
        var removeItemButtonText = removeItemButton.innerText();
        assert removeItemButtonText.equals("Remove");
    }

    public void clickCartIcon() {
        cartIcon.click();
    }

    public void clickSortingDropdownAndSelectValue(String sortingOption) {
        sortingDropdown.click();
        sortingDropdown.selectOption(new SelectOption().setValue(sortingOption));

    }

    public void verifyItemsOrderedByAtoZ() {
        page.waitForTimeout(2000);
        List<String> itemNames = new ArrayList<>(inventoryItemName
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
}

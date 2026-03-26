package saucedemo.com.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import java.util.Comparator;
import java.util.List;

public class ProductPage extends BasePage {

    private static final String PAGE_TITLE = ".app_logo";
    private static final String ADD_TO_CART = "#add-to-cart";
    private static final String REMOVE_ITEM_BUTTON = "#remove";
    private static final String CART_ICON = "[data-test='shopping-cart-link']";
    private static final String SORTING_DROPDOWN = ".product_sort_container";
    private static final String INVENTORY_ITEM_NAME = ".inventory_item_name";
    private static final String PRICE_ELEMENT = ".inventory_item_price";
    private static final String BURGER_MENU = "#react-burger-menu-btn";
    private static final String LOGOUT_SIDEBAR_BUTTON = "#logout_sidebar_link";

    public ProductPage(Page page) {
        super(page);
    }

    public String getProductLogoText() {
        return page.innerText(PAGE_TITLE);
    }

    public void openItemPage(String itemName) {
        String inventoryItemSelector = String.format("%s:text('%s')", INVENTORY_ITEM_NAME, itemName);
        page.locator(inventoryItemSelector).click();
    }

    public void addItemToCart() {
        page.click(ADD_TO_CART);
    }

    public void removeItemFromCartButtonExist() {
        var removeItemButtonText = page.innerText(REMOVE_ITEM_BUTTON);
        assert removeItemButtonText.equals("Remove");
    }

    public void clickCartIcon() {
        page.click(CART_ICON);
    }

    public void clickSortingDropdownAndSelectValue(String sortingOption) {
        page.selectOption(SORTING_DROPDOWN, new SelectOption().setValue(sortingOption));
    }

    public void verifyItemsOrderedByAtoZ() {
        page.waitForTimeout(2000);
        List<String> itemNames = page.locator(INVENTORY_ITEM_NAME).allTextContents();

        if (isSortedZToA(itemNames)) {
            System.out.println("The items are sorted correctly by Z to A.");
        } else {
            System.out.println("The items are NOT sorted by Z to A.");
        }
    }

    private static boolean isSortedZToA(List<String> list) {
        List<String> sortedList = list.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        return list.equals(sortedList);
    }

    public void verifyItemsPriceOrderedLowHigh() {
        List<String> priceTexts = page.locator(PRICE_ELEMENT).allInnerTexts();
        List<Double> prices = priceTexts.stream()
                .map(price -> Double.parseDouble(price.replace("$", "").trim()))
                .toList();

        boolean isSorted = isSortedLowToHigh(prices);
        System.out.println("Are the prices sorted from low to high? " + isSorted);
    }

    private static boolean isSortedLowToHigh(List<Double> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) return false;
        }
        return true;
    }

    public void logout() {
        page.click(BURGER_MENU);
        page.click(LOGOUT_SIDEBAR_BUTTON);
    }

    public void verifyLogout() {
        assert page.isVisible("#login-button");
    }
}
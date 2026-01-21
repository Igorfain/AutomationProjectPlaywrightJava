package saucedemo.com.tests;
import common.infra.ConsoleReporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.com.infra.base.BaseTest;
import saucedemo.com.steps.CartPageSteps;
import saucedemo.com.steps.ProductPageSteps;

public class OrderingTests extends BaseTest {

    private CartPageSteps cartPageSteps;
    private ProductPageSteps productPageSteps;
    @BeforeMethod

    public void setUpTest() {

        productPageSteps = new ProductPageSteps(page);
        cartPageSteps = new CartPageSteps(page);
    }
    @Test(description = "Order from A to Z and verify it was ordered fine")
    public void orderFromAtoZ(){
        String sortingOption = "za";
        productPageSteps.ClickTheSortingDropdownAndSelectValue(sortingOption);
        productPageSteps.verifyItemsOrderedAtoZ();
    }

    @Test(description = "Order price from low to high and verify it was ordered fine")
    public void orderPriceLowToHigh(){
        String sortingOption = "lohi";
        productPageSteps.ClickTheSortingDropdownAndSelectValue(sortingOption);
        productPageSteps.verifyItemsPriceOrderedLowHigh();
    }

}

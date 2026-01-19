package saucedemo.com.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.com.infra.ConsoleReporter;
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
        ConsoleReporter.log("Step 1 - Order from A to Z");
        productPageSteps.ClickTheSortingDropdownAndSelectValue(sortingOption);
        ConsoleReporter.log("Step 2 - Verify items ordered from Z to A");
        productPageSteps.verifyItemsOrderedAtoZ();
    }

    @Test(description = "Order price from low to high and verify it was ordered fine")
    public void orderPriceLowToHigh(){
        String sortingOption = "lohi";
        ConsoleReporter.log("Step 1 - Order price from low to High");
        productPageSteps.ClickTheSortingDropdownAndSelectValue(sortingOption);
        ConsoleReporter.log("Step 2 - Verify price was ordered low to High");
        productPageSteps.verifyItemsPriceOrderedLowHigh();
    }

}

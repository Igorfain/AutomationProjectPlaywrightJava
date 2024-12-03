package saucedemo.com.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.com.infra.ConsoleReporter;
import saucedemo.com.steps.CartPageSteps;
import saucedemo.com.steps.ProductPageSteps;
public class OrderingTests extends BaseTest{

    private CartPageSteps cartPageSteps;
    private ProductPageSteps productPageSteps;
    @BeforeMethod

    public void setUpTest() {

        productPageSteps = new ProductPageSteps(page);
        cartPageSteps = new CartPageSteps(page);
    }
    @Test(description = "Order from A to Z and verify it was ordered fine")
    private void orderFromAtoZ(){
        ConsoleReporter.log("Order from A to Z");
        String sortingOption = "za";
        productPageSteps.ClickTheSortingDropdownAndSelectValue(sortingOption);
        int i = 0;
    }

}

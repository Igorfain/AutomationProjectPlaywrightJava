package saucedemo.com.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import saucedemo.com.infra.ConsoleReporter;
import saucedemo.com.steps.LoginSteps;
import saucedemo.com.steps.ProductPageSteps;

public class PlaywrightTests extends BaseTest {

    private LoginSteps loginSteps;
     private ProductPageSteps productPageSteps;

    @BeforeClass
    public void setUpTest() {

        productPageSteps = new ProductPageSteps(page);
    }

    @Test(description = "Verify product page logo")
    public void testProductPageLogo() {
        ConsoleReporter.log("Step 1 - Verify logo");
        String logoText = productPageSteps.getLogoText();
        System.out.println("Retrieved logo text: " + logoText);
        assert logoText.equals("Swag Labs") : "Logo text does not match. Expected 'Swag Labs' but found '" + logoText + "'";
        ConsoleReporter.log("Test completed successfully");
    }

    @Test(description = "Open item page and add to cart")
    public void SelectItemAndAddToCard() {
        ConsoleReporter.log("Step 1 - Open item page");
        var itemName1 = "Sauce Labs Backpack";
        productPageSteps.openItemPage(itemName1);
        ConsoleReporter.log("Step 2 - Open item to the cart");
        productPageSteps.addItemToTheCart();
        ConsoleReporter.log("Step 3 - Verify Remove button is displaying");
        productPageSteps.verifyRemoveButtonIsDisplaying();
        ConsoleReporter.log("Test completed successfully");

    }


}

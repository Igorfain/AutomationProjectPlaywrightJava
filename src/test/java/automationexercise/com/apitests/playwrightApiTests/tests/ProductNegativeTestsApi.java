package automationexercise.com.apitests.playwrightApiTests.tests;

import automationexercise.com.apitests.playwrightApiTests.base.BasePlaywrightApiTest;
import automationexercise.com.apitests.playwrightApiTests.assertions.ProductApiAssertions;
import automationexercise.com.apitests.playwrightApiTests.steps.ProductApiSteps;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import saucedemo.com.infra.ConsoleReporter;

public class ProductNegativeTestsApi extends BasePlaywrightApiTest {

    private ProductApiSteps productApi;

    @BeforeClass
    public void init() {
        productApi = new ProductApiSteps(requestContext);
    }

    @Test(description = "Search product with empty value")
    public void searchProductEmptyValue() {
        ConsoleReporter.log("STEP: Search product with empty value");
        APIResponse response = productApi.searchProductEmpty();
        ProductApiAssertions.assertEmptySearchReturnsAllProducts(response);
    }

    @Test(description = "Search product without parameter")
    public void searchProductWithoutParam() {
        ConsoleReporter.log("STEP: Search product without parameter");
        APIResponse response = productApi.searchProductWithoutParam();
        ProductApiAssertions.assertMissingParamError(response);
    }
}

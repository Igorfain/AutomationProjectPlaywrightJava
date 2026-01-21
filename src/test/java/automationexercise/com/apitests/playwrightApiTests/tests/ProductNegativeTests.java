package automationexercise.com.apitests.playwrightApiTests.tests;

import automationexercise.com.apitests.playwrightApiTests.base.BasePlaywrightApiTest;
import automationexercise.com.apitests.playwrightApiTests.assertions.ProductApiAssertions;
import automationexercise.com.apitests.playwrightApiTests.steps.ProductApiSteps;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductNegativeTests extends BasePlaywrightApiTest {

    private ProductApiSteps productApi;

    @BeforeClass
    public void init() {
        productApi = new ProductApiSteps(requestContext);
    }

    @Test(description = "Search product with empty value")
    public void searchProductEmptyValue() {
        APIResponse response = productApi.searchProductEmpty();
        ProductApiAssertions.assertEmptySearchReturnsAllProducts(response);
    }

    @Test(description = "Search product without parameter")
    public void searchProductWithoutParam() {
        APIResponse response = productApi.searchProductWithoutParam();
        ProductApiAssertions.assertMissingParamError(response);
    }
}

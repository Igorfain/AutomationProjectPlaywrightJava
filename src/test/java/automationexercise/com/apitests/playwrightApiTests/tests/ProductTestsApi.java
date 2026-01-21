package automationexercise.com.apitests.playwrightApiTests.tests;

import automationexercise.com.apitests.playwrightApiTests.base.BasePlaywrightApiTest;
import automationexercise.com.apitests.playwrightApiTests.assertions.ProductApiAssertions;
import automationexercise.com.apitests.playwrightApiTests.steps.ProductApiSteps;
import automationexercise.com.apitests.playwrightApiTests.data.ProductTestData;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import saucedemo.com.infra.ConsoleReporter;

public class ProductTestsApi extends BasePlaywrightApiTest {

    private ProductApiSteps productApi;

    @BeforeClass
    public void initSteps() {
        productApi = new ProductApiSteps(requestContext);
    }

    @Test(description = "Verify product list using Playwright API")
    public void testGetProductsWithPlaywright() {
        ConsoleReporter.log("STEP: Verify product list using Playwright API");
        APIResponse response = productApi.getProductsList();
        ProductApiAssertions.assertProductsListResponse(response);
    }

    @Test(description = "Verify search product using Playwright API (Form Data)")
    public void testSearchProductPlaywright() {
        ConsoleReporter.log("STEP: Verify search product using Playwright API (Form Data)");
        APIResponse response = productApi.searchProduct(ProductTestData.SEARCH_TSHIRT);
        ProductApiAssertions.assertSearchProductResponse(response);
    }

}

package apitestsAutomationExercise.apitests.playwrightApiTests.tests;

import apitestsAutomationExercise.apitests.playwrightApiTests.base.BasePlaywrightApiTest;
import apitestsAutomationExercise.apitests.playwrightApiTests.assertions.ProductApiAssertions;
import apitestsAutomationExercise.apitests.playwrightApiTests.steps.ProductApiSteps;
import apitestsAutomationExercise.apitests.playwrightApiTests.data.ProductTestData;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductTestsApi extends BasePlaywrightApiTest {

    private ProductApiSteps productApi;

    @BeforeClass
    public void initSteps() {
        productApi = new ProductApiSteps(requestContext);
    }

    @Test(description = "Verify product list ")
    public void testGetProductsWithPlaywright() {
        logTestStep();
        APIResponse response = productApi.getProductsList();
        ProductApiAssertions.assertProductsListResponse(response);
    }

    @Test(description = "Verify search product ")
    public void testSearchProductPlaywright() {
        logTestStep();
        APIResponse response = productApi.searchProduct(ProductTestData.SEARCH_TSHIRT);
        ProductApiAssertions.assertSearchProductResponse(response);
    }

}

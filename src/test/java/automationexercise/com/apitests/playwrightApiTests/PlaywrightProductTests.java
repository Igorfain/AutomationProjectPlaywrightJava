package automationexercise.com.apitests.playwrightApiTests;

import com.microsoft.playwright.APIResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaywrightProductTests extends BasePlaywrightApiTest {

    private ProductApiSteps productApi;

    @BeforeClass
    public void initSteps() {
        productApi = new ProductApiSteps(requestContext);
    }

    @Test(description = "Verify product list using Playwright API")
    public void testGetProductsWithPlaywright() {
        APIResponse response = productApi.getProductsList();
        ProductApiAssertions.assertProductsListResponse(response);
    }

    @Test(description = "Verify search product using Playwright API (Form Data)")
    public void testSearchProductPlaywright() {
        APIResponse response = productApi.searchProduct(ProductTestData.SEARCH_TSHIRT);
        ProductApiAssertions.assertSearchProductResponse(response);
    }

}

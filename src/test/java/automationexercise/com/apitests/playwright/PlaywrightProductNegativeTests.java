package automationexercise.com.apitests.playwright;

import com.microsoft.playwright.APIResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaywrightProductNegativeTests extends BasePlaywrightApiTest {

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

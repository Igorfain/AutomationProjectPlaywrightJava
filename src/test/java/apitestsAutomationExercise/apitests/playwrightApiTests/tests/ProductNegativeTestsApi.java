package apitestsAutomationExercise.apitests.playwrightApiTests.tests;

import apitestsAutomationExercise.apitests.playwrightApiTests.base.BasePlaywrightApiTest;
import apitestsAutomationExercise.apitests.playwrightApiTests.assertions.ProductApiAssertions;
import apitestsAutomationExercise.apitests.playwrightApiTests.steps.ProductApiSteps;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductNegativeTestsApi extends BasePlaywrightApiTest {

    private ProductApiSteps productApi;

    @BeforeClass
    public void init() {
        productApi = new ProductApiSteps(requestContext);
    }

    @Test(description = "Search product with empty value")
    public void searchProductEmptyValue() {
        logTestStep();
        APIResponse response = productApi.searchProductEmpty();
        ProductApiAssertions.assertEmptySearchReturnsAllProducts(response);
    }

    @Test(description = "Search product without parameter")
    public void searchProductWithoutParam() {
        logTestStep();
        APIResponse response = productApi.searchProductWithoutParam();
        ProductApiAssertions.assertMissingParamError(response);
    }
}

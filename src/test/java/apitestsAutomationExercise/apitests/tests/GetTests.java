package apitestsAutomationExercise.apitests.tests;

import apitestsAutomationExercise.apitests.BaseApiTest;
import apitestsAutomationExercise.apitests.steps.ProductApiSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Story("Product API")
public class GetTests extends BaseApiTest {

    private ProductApiSteps productApiSteps;

    @BeforeClass
    public void initSteps() {
        productApiSteps = new ProductApiSteps(AUTOMATION_EXERCISE_URI);
    }

    @Test(description = "Verify /api/productsList returns valid products list")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    public void testGetAllProductList() {
        productApiSteps.verifyProductsListReturnsValidList();
    }

    @Test(description = "Verify that /api/brandsList returns valid brand list")
    @Owner("Igor")
    @Link(name = "API List", url = "https://automationexercise.com/api_list")
    public void testGetAllBrandsList() {
        productApiSteps.verifyBrandsListReturnsValidList();
    }

    @Test(description = "Verify POST /api/productsList returns 405 in response body")
    @Owner("Igor")
    public void testPostToProductsList_NotAllowed() {
        productApiSteps.verifyPostToProductsListIsNotAllowed();
    }
}

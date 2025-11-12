package automationexercise.com.apitests.tests;

import automationexercise.com.apitests.BaseTest;
import automationexercise.com.apitests.services.Endpoints;
import automationexercise.com.apitests.helpers.ApiRequestHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostTests extends BaseTest {

    private final ApiRequestHelper apiRequestHelper = new ApiRequestHelper();

    @Test
    public void testSearchProduct() {
        // Send POST request to search for a product
        Response response = apiRequestHelper.sendPostFormRequest(
                AUTOMATION_EXERCISE_URI + Endpoints.SEARCH_PRODUCT,
                "search_product",
                "Men Tshirt"
        );

        // Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("Men Tshirt"),
                "Response body should contain 'Men Tshirt'");

        // Optional debug output
        System.out.println("Response Body:\n" + response.getBody().asString());
    }
}

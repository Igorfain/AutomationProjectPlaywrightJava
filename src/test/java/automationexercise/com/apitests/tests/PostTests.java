package automationexercise.com.apitests.tests;

import apitests.BaseTest;
import apitests.services.ApiService;
import apitests.utils.JsonUtils;
import automationexercise.com.apitests.services.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostTests extends BaseTest {

    private final ApiService apiService = new ApiService();

    @Test
    public void testSearchProduct() {
        // Define API endpoint
        String apiUrl = "https://automationexercise.com/api/searchProduct";

        // Send POST request with the required parameter
        Response response = RestAssured.given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("search_product", "tshirt")
                .log().all() // Log the request details
                .post(apiUrl);

        // Log the response details
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders());
        System.out.println("Response Body: " + response.getBody().asString());

        // Validate response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200.");

        // Validate response body contains the searched product
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("tshirt"), "Response should contain 'tshirt'.");
    }
}

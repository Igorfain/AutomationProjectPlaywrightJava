package automationexercise.com.apitests.tests;

import automationexercise.com.apitests.BaseTest;
import automationexercise.com.apitests.services.ApiService;
import automationexercise.com.apitests.services.Endpoints;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GetTests extends BaseTest {

    private final ApiService apiService = new ApiService();

    @Test
    public void testGetAllProductList() {
        // Send GET request to fetch all products
        Response response = apiService.sendGetRequest(Endpoints.GET_ALL_PRODUCTS);

        // Log the response body
        System.out.println("Response Body is: " + response.getBody().asString());

        // Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        // Parse the response body to a Map
        Gson gson = new Gson();
        Map<String, Object> responseBody = gson.fromJson(response.getBody().asString(), Map.class);

        // Validate the response contains 'responseCode' and 'products'
        Assert.assertTrue(responseBody.containsKey("responseCode"), "Response should contain 'responseCode'");
        Assert.assertEquals(((Double) responseBody.get("responseCode")).intValue(), 200, "Response code should be 200");
        Assert.assertTrue(responseBody.containsKey("products"), "Response should contain 'products'");

        // Validate that the 'products' list is not empty
        List<Map<String, Object>> products = (List<Map<String, Object>>) responseBody.get("products");
        Assert.assertFalse(products.isEmpty(), "Products list should not be empty");

        // Validate the first product in the list
        Map<String, Object> firstProduct = products.get(0);
        Assert.assertTrue(firstProduct.containsKey("id"), "First product should contain 'id'");
        Assert.assertTrue(firstProduct.containsKey("name"), "First product should contain 'name'");
        Assert.assertTrue(firstProduct.containsKey("price"), "First product should contain 'price'");
        Assert.assertTrue(firstProduct.containsKey("brand"), "First product should contain 'brand'");

        // Log the first product details
        System.out.println("First Product Details: " + firstProduct);
    }
}

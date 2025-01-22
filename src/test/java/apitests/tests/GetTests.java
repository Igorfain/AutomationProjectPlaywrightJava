package tests;

import apitests.BaseTest;
import apitests.services.ApiService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetTests extends BaseTest {

    private final ApiService apiService = new ApiService();

    @Test
    public void testGetRequest() {
        Response response = apiService.sendGetRequest("/posts/1");
        System.out.println("Response Body is: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.getBody().asString().contains("userId"), "Response should contain 'userId'");
    }
}

package apitests.tests.jsonplaceholderapi;

import apitests.BaseApiTest;
import apitests.services.ApiService;
import apitests.utils.JsonUtils;
import common.infra.ConsoleReporter;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostTests extends BaseApiTest {

    private final ApiService apiService = new ApiService();

    @Test(description = "Verify that a POST request to /posts creates a new resource and returns 201 Created with a valid 'id'")
    public void testPostRequest() {
        logTestStep();
        String requestBody = JsonUtils.createPostRequestBody("foo", "bar", 1);
        Response response = apiService.sendPostRequest(JSON_PLACEHOLDER_URI+"/posts", requestBody);
        System.out.println("Response Body is: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        Assert.assertTrue(response.getBody().asString().contains("id"), "Response should contain 'id'");
    }
}

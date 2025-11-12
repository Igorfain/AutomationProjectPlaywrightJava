package apitests.tests.jsonplaceholderapi;

import apitests.BaseTest;
import apitests.services.ApiService;
import apitests.utils.JsonUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostTests extends BaseTest {

    private final ApiService apiService = new ApiService();

    @Test
    public void testPostRequest() {
        String requestBody = JsonUtils.createPostRequestBody("foo", "bar", 1);
        Response response = apiService.sendPostRequest(JSON_PLACEHOLDER_URI+"/posts", requestBody);
        System.out.println("Response Body is: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        Assert.assertTrue(response.getBody().asString().contains("id"), "Response should contain 'id'");
    }
}

package apitests.tests.jsonplaceholderapi;

import apitests.BaseApiTest;
import apitests.services.ApiService;
import common.infra.ConsoleReporter;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetTests extends BaseApiTest {

    private final ApiService apiService = new ApiService();

    @Test(description = "Verify that a GET request to /posts/{id} returns 200 OK and the response body contains 'userId'")
    public void testGetRequest() {
        logTestStep();
        Response response = apiService.sendGetRequest(JSON_PLACEHOLDER_URI+"/posts/1");
        System.out.println("Response Body is: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.getBody().asString().contains("userId"), "Response should contain 'userId'");
    }
}

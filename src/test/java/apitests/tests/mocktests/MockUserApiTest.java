package apitests.tests.mocktests;

import apitests.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MockUserApiTest extends BaseApiTest {

    @Test(description = "Verify that a new user is created successfully via mock POST request")
    public void createUserTest() {
        logTestStep();
        String body = "{ \"name\": \"FreeWind\" }";

        Response response = mock.post("/user/create", body);

        String resp = response.getBody().asString();
        System.out.println("Mock POST response: " + resp);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(resp.contains("\"result\":\"User created\""));
        Assert.assertTrue(resp.contains("\"id\":777"));
    }

    @Test(description = "Verify that user details are successfully updated via mock PUT request")
    public void updateUserTest() {
        logTestStep();
        String body = "{ \"id\": 123, \"name\": \"FreeWind Updated\" }";

        Response response = mock.put("/user/update", body);

        String resp = response.getBody().asString();
        System.out.println("Mock PUT response: " + resp);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(resp.contains("\"result\":\"User updated\""));
        Assert.assertTrue(resp.contains("\"updated\":true"));
    }

    @Test(description = "Verify that a user is successfully deleted via mock DELETE request")
    public void deleteUserTest() {
        logTestStep();
        Response response = mock.deleteUser(123);

        String body = response.getBody().asString();
        System.out.println("Mock DELETE response: " + body);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(body.contains("\"deleted\":true"));
        Assert.assertTrue(body.contains("\"result\":\"User deleted\""));
    }

    @Test(description = "Verify that user details are successfully retrieved via mock GET request")
    public void getMockUser() {
        logTestStep();
        Response response = mock.getUser(123);

        String body = response.getBody().asString();
        System.out.println("Mock response: " + body);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(body.contains("\"id\":123"));
        Assert.assertTrue(body.contains("\"name\":\"FreeWind\""));
        Assert.assertTrue(body.contains("\"role\":\"admin\""));
    }
}
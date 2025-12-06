package apitests.tests.mocktests;

import apitests.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MockUserTest extends BaseTest {

    @Test
    public void createUserTest() {
        String body = "{ \"name\": \"FreeWind\" }";

        Response response = mock.post("/user/create", body);

        String resp = response.getBody().asString();
        System.out.println("Mock POST response: " + resp);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(resp.contains("\"result\":\"User created\""));
        Assert.assertTrue(resp.contains("\"id\":777"));
    }

    @Test
    public void updateUserTest() {
        String body = "{ \"id\": 123, \"name\": \"FreeWind Updated\" }";

        Response response = mock.put("/user/update", body);

        String resp = response.getBody().asString();
        System.out.println("Mock PUT response: " + resp);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(resp.contains("\"result\":\"User updated\""));
        Assert.assertTrue(resp.contains("\"updated\":true"));
    }

    @Test
    public void deleteUserTest() {
        Response response = mock.deleteUser(123);

        String body = response.getBody().asString();
        System.out.println("Mock DELETE response: " + body);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(body.contains("\"deleted\":true"));
        Assert.assertTrue(body.contains("\"result\":\"User deleted\""));
    }

    @Test
    public void getMockUser() {
        Response response = mock.getUser(123);

        String body = response.getBody().asString();
        System.out.println("Mock response: " + body);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(body.contains("\"id\":123"));
        Assert.assertTrue(body.contains("\"name\":\"FreeWind\""));
        Assert.assertTrue(body.contains("\"role\":\"admin\""));
    }
}

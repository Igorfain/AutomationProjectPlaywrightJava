package ApiTests.helpers;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {

    // GET request validation
    public void validateUserDetailsResponse(Response response) {
        System.out.println("User Details Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("userId"), "Response should contain 'userId'");
    }

// POST request validation
    public void validateUserPostResponse(Response response) {
        System.out.println("User Post Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        Assert.assertTrue(response.getBody().asString().contains("id"), "Response should contain 'id'");
    }

    // Put request validation
    public void validateUpdateUserResponse(Response response) {
        System.out.println("Update User Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("updated"), "Response should indicate an update was successful");
    }

    // Delete request validation
    public void validateDeleteUserResponse(Response response) {
        System.out.println("Delete User Response: " + response.getBody().asString());
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204,
                "Expected status code 200 or 204");

        String responseBody = response.getBody().asString().trim();
        Assert.assertTrue(responseBody.isEmpty() || responseBody.equals("{}"),
                "Response body should be empty or contain an empty JSON object");


    }


}

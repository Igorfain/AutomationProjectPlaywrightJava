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

    public void validateNotFoundResponse(Response response) {
        // Логируем содержимое тела ответа для удобства отладки
        System.out.println("Update User Response: " + response.getBody().asString());

        // Проверяем, что статус-код равен 404
        Assert.assertEquals(response.getStatusCode(), 404, "Expected status code 404 for non-existent resource");

        // Проверяем, что тело ответа пустое или содержит сообщение об ошибке "Not Found"
        String responseBody = response.getBody().asString();
        if (!responseBody.isEmpty() && !responseBody.equals("{}")) {
            String expectedErrorTitle = "Not Found";
            Assert.assertTrue(responseBody.contains(expectedErrorTitle),
                    "Expected error title 'Not Found' not found in response body");
        } else {
            Assert.assertTrue(responseBody.isEmpty() || responseBody.equals("{}"),
                    "Expected empty response body or '{}' for non-existent user");
        }
    }

    public void validateBadRequestResponse(Response response) {

        System.out.println("Update User Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400 for bad request");

    }

    // For FakeRestApi

    public void validateBookNotFoundResponse(Response response) {
        System.out.println("Get Book Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 404, "Expected status code 404 for non-existent book");

    }

    public void validateAuthorResponse(Response response) {
        System.out.println("Author Response: "+"Response Code: "+response.getStatusCode()+", "+"Response Body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for existing author");

        // Validation of fields - id, idBook, firstName , lastName
        Assert.assertTrue(response.getBody().asString().contains("\"id\":"), "Response does not contain 'id'");
        Assert.assertTrue(response.getBody().asString().contains("\"idBook\":"), "Response does not contain 'idBook'");
        Assert.assertTrue(response.getBody().asString().contains("\"firstName\":"), "Response does not contain 'firstName'");
        Assert.assertTrue(response.getBody().asString().contains("\"lastName\":"), "Response does not contain 'lastName'");
    }

    public void validateCoverPhotosResponse(Response response) {
        System.out.println("Cover Photo Response: "+"Response Code: "+response.getStatusCode()+", "+"Response Body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for existing Cover Photo");

    }



}

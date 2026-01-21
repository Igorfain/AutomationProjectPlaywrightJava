package apitests.tests.jsonplaceholderapi;

import apitests.BaseApiTest;
import apitests.services.Endpoints;
import apitests.helpers.ApiRequestHelper;
import apitests.helpers.ResponseValidator;
import org.testng.annotations.Test;
import saucedemo.com.infra.ConsoleReporter;

import static apitests.utils.JsonUtils.createPostRequestBody;

public class UserTests extends BaseApiTest {

    private final ApiRequestHelper apiRequestHelper = new ApiRequestHelper();
    private final ResponseValidator responseValidator = new ResponseValidator();

    @Test(description = "Verify that fetching user details by ID returns the correct data and status code")
    public void testGetUserDetails() {
        ConsoleReporter.log("STEP: Verify that fetching user details by ID returns the correct data and status code");
        var response = apiRequestHelper.sendGetRequest(JSON_PLACEHOLDER_URI, Endpoints.GET_POST_BY_ID("2"));
        responseValidator.validateUserDetailsResponse(response);
    }

    @Test(description = "Verify that creating a new post for a user returns 201 Created and valid response body")
    public void testCreateUserPost() {
        ConsoleReporter.log("STEP: Verify that creating a new post for a user returns 201 Created and valid response body");
        String requestBody = createPostRequestBody("test title", "test body content", 2);
        var response = apiRequestHelper.sendPostRequest(JSON_PLACEHOLDER_URI, Endpoints.CREATE_POST, requestBody);
        responseValidator.validateUserPostResponse(response);
    }

    @Test(description = "Verify that updating existing user post details via PUT request is successful")
    public void testUpdateUserDetails() {
        ConsoleReporter.log("STEP: Verify that updating existing user post details via PUT request is successful");
        String requestBody = createPostRequestBody("updated title", "updated body content", 2);
        var response = apiRequestHelper.sendPutRequest(JSON_PLACEHOLDER_URI, Endpoints.GET_POST_BY_ID("2"), requestBody);
        responseValidator.validateUpdateUserResponse(response);
    }

    @Test(description = "Verify that a user post can be successfully deleted and returns the correct status")
    public void testDeleteUser() {
        ConsoleReporter.log("STEP: Verify that a user post can be successfully deleted and returns the correct status");
        var response = apiRequestHelper.sendDeleteRequest(JSON_PLACEHOLDER_URI, Endpoints.GET_POST_BY_ID("2"));
        responseValidator.validateDeleteUserResponse(response);
    }
}
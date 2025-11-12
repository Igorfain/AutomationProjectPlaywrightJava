package apitests.tests.jsonplaceholderapi;

import apitests.BaseTest;
import apitests.services.Endpoints;
import apitests.helpers.ApiRequestHelper;
import apitests.helpers.ResponseValidator;
import org.testng.annotations.Test;

import static apitests.utils.JsonUtils.createPostRequestBody;

public class UserTests extends BaseTest {

    private final ApiRequestHelper apiRequestHelper = new ApiRequestHelper();
    private final ResponseValidator responseValidator = new ResponseValidator();

    @Test
    public void testGetUserDetails() {
        var response = apiRequestHelper.sendGetRequest(JSON_PLACEHOLDER_URI,Endpoints.GET_POST_BY_ID("2"));
        responseValidator.validateUserDetailsResponse(response);
    }

    @Test
    public void testCreateUserPost() {
        String requestBody = createPostRequestBody("test title", "test body content", 2);
        var response = apiRequestHelper.sendPostRequest(JSON_PLACEHOLDER_URI,Endpoints.CREATE_POST, requestBody);
        responseValidator.validateUserPostResponse(response);
    }

    @Test
    public void testUpdateUserDetails() {

        String requestBody = createPostRequestBody("updated title", "updated body content", 2);
        var response = apiRequestHelper.sendPutRequest(JSON_PLACEHOLDER_URI,Endpoints.GET_POST_BY_ID("2"), requestBody);
        responseValidator.validateUpdateUserResponse(response);
    }

    @Test
    public void testDeleteUser() {
        var response = apiRequestHelper.sendDeleteRequest(JSON_PLACEHOLDER_URI,Endpoints.GET_POST_BY_ID("2"));
        responseValidator.validateDeleteUserResponse(response);
    }

}

package ApiTests.tests;

import ApiTests.BaseTest;
import ApiTests.services.Endpoints;
import ApiTests.helpers.ApiRequestHelper;
import ApiTests.helpers.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static ApiTests.utils.JsonUtils.createPostRequestBody;

public class UserTests extends BaseTest {

    private final ApiRequestHelper apiRequestHelper = new ApiRequestHelper();
    private final ResponseValidator responseValidator = new ResponseValidator();

    @Test
    public void testGetUserDetails() {
        Response response = apiRequestHelper.sendGetRequest(Endpoints.GET_POST_BY_ID("2"));
        responseValidator.validateUserDetailsResponse(response);
    }

    @Test
    public void testCreateUserPost() {
        String requestBody = createPostRequestBody("test title", "test body content", 2);
        Response response = apiRequestHelper.sendPostRequest(Endpoints.CREATE_POST, requestBody);
        responseValidator.validateUserPostResponse(response);
    }

    @Test
    public void testUpdateUserDetails() {
        // Создание JSON тела для обновления данных
        String requestBody = createPostRequestBody("updated title", "updated body content", 2);

        // Отправка PUT-запроса для обновления данных пользователя
        Response response = apiRequestHelper.sendPutRequest(Endpoints.GET_POST_BY_ID("2"), requestBody);

        // Проверка ответа
        responseValidator.validateUpdateUserResponse(response);
    }

    @Test
    public void testDeleteUser() {
        Response response = apiRequestHelper.sendDeleteRequest(Endpoints.GET_POST_BY_ID("2"));
        responseValidator.validateDeleteUserResponse(response);
    }

//    private final String token = "your_access_token_here"; // Замените на ваш реальный токен
//
//    @Test
//    public void testGetUserDetailsWithAuth() {
//        Response response = apiRequestHelper.sendGetRequestWithAuth(Endpoints.GET_POST_BY_ID("2"), token);
//        responseValidator.validateUserDetailsResponse(response);
//    }
//
//    @Test
//    public void testCreateUserPostWithAuth() {
//        String requestBody = "{\"title\": \"auth title\", \"body\": \"auth body content\", \"userId\": 2}";
//        Response response = apiRequestHelper.sendPostRequestWithAuth(Endpoints.CREATE_POST, token, requestBody);
//        responseValidator.validateUserPostResponse(response);
//    }
}

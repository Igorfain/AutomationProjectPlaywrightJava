package apitestsAutomationExercise.apitests.steps;

import apitestsAutomationExercise.apitests.data.UserTestData;
import apitestsAutomationExercise.apitests.helpers.ApiRequestHelper;
import apitestsAutomationExercise.apitests.services.Endpoints;
import common.infra.ConsoleReporter;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

public class UserApiSteps {

    private final ApiRequestHelper apiRequestHelper;
    private final String baseUri;

    public UserApiSteps(String baseUri) {
        this.apiRequestHelper = new ApiRequestHelper();
        this.baseUri = baseUri;
    }

    @Step("Verify login with valid credentials")
    public UserApiSteps verifyLoginWithValidCredentials(String email, String password) {
        ConsoleReporter.log("Verify login with valid credentials");
        Map<String, String> loginFormData = UserTestData.buildLoginFormData(email, password);

        Response loginResponse = apiRequestHelper.sendPostRequestWithFormData(
                baseUri + Endpoints.VERIFY_LOGIN,
                loginFormData
        );

        Assert.assertEquals(loginResponse.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(loginResponse.getBody().asString().contains("User exists!"),
                "Response body should contain 'User exists!'");
        return this;
    }

    @Step("Verify update user account is successful")
    public UserApiSteps verifyUpdateUserAccountIsSuccessful(String email, String password) {
        ConsoleReporter.log("Verify update user account is successful");
        Map<String, String> updateUserFormData = UserTestData.buildUpdateUserFormData(email, password);

        Response updateUserResponse = apiRequestHelper.sendPutRequestWithFormData(
                baseUri + Endpoints.UPDATE_ACCOUNT,
                updateUserFormData
        );

        Assert.assertEquals(updateUserResponse.getStatusCode(), 200, "Expected status code 200 for user update");
        String responseMessage = updateUserResponse.jsonPath().getString("message");
        Assert.assertEquals(responseMessage, "User updated!",
                "Response message should indicate user was updated");
        return this;
    }
}

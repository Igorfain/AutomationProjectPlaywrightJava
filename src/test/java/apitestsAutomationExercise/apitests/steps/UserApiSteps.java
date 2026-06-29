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

        int actualLoginHttpStatus = loginResponse.getStatusCode();
        Assert.assertEquals(actualLoginHttpStatus, 200,
                "HTTP status mismatch. Expected: 200, Actual: " + actualLoginHttpStatus);

        String actualLoginResponseBody = loginResponse.getBody().asString();
        Assert.assertTrue(actualLoginResponseBody.contains("User exists!"),
                "Response body should contain 'User exists!'. Actual: " + actualLoginResponseBody);
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

        int actualUpdateHttpStatus = updateUserResponse.getStatusCode();
        Assert.assertEquals(actualUpdateHttpStatus, 200,
                "HTTP status mismatch. Expected: 200, Actual: " + actualUpdateHttpStatus);

        String actualUpdateResponseMessage = updateUserResponse.jsonPath().getString("message");
        Assert.assertEquals(actualUpdateResponseMessage, "User updated!",
                "Response message mismatch. Expected: 'User updated!', Actual: " + actualUpdateResponseMessage);
        return this;
    }
}

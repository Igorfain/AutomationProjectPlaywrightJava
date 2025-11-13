package automationexercise.com.apitests.tests;

import automationexercise.com.apitests.BaseTest;
import automationexercise.com.apitests.helpers.ApiRequestHelper;
import automationexercise.com.apitests.services.Endpoints;
import automationexercise.com.utils.ConfigPaths;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PutTests extends BaseTest {

    private final ApiRequestHelper apiRequestHelper = new ApiRequestHelper();
    private String email;
    private String password;

    @BeforeClass
    public void readConfig() throws IOException {
        JSONObject config = new JSONObject(
                new String(Files.readAllBytes(Paths.get(ConfigPaths.MAIN_CONFIG_PATH)))
        );

        email = config.getString("username");
        password = config.getString("password");
    }

    @Test(description = "Update user account details")
    @Story("Positive Test - Update existing user account")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Igor")
    public void testUpdateUserAccount() {
        Map<String, String> formData = new HashMap<>();
        formData.put("email", email);
        formData.put("password", password);
        formData.put("name", "TestUser");
        formData.put("title", "Mr");
        formData.put("birth_date", "10");
        formData.put("birth_month", "January");
        formData.put("birth_year", "1990");
        formData.put("firstname", "Igor");
        formData.put("lastname", "Test");
        formData.put("company", "TestCorp");
        formData.put("address1", "123 Test Lane");
        formData.put("address2", "Apt 4B");
        formData.put("country", "United States");
        formData.put("zipcode", "10001");
        formData.put("state", "New York");
        formData.put("city", "New York");
        formData.put("mobile_number", "1234567890");
        Response response = apiRequestHelper.sendPutRequestWithFormData(AUTOMATION_EXERCISE_URI+Endpoints.UPDATE_ACCOUNT, formData);
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for user update");
        String responseMessage = response.jsonPath().getString("message");
        Assert.assertEquals(responseMessage, "User updated!", "Response message should indicate user was updated");
    }
}
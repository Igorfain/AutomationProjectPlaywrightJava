package automationexercise.com.apitests.tests;

import automationexercise.com.apitests.BaseApiTest;
import automationexercise.com.apitests.helpers.ApiRequestHelper;
import automationexercise.com.apitests.services.Endpoints;
import automationexercise.com.utils.ConfigReader;
import com.google.gson.Gson;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostTests extends BaseApiTest {

    private final ApiRequestHelper apiRequestHelper = new ApiRequestHelper();

    private String email;
    private String password;

    @BeforeClass
    public void readConfig() throws IOException {
        email = ConfigReader.getEnv("LOGIN_USERNAME");
        password = ConfigReader.getEnv("LOGIN_PASSWORD");

        Assert.assertNotNull(email, "`LOGIN_USERNAME` env is not set");
        Assert.assertFalse(email.isBlank(), "`LOGIN_USERNAME` env is blank");

        Assert.assertNotNull(password, "`LOGIN_PASSWORD` env is not set");
        Assert.assertFalse(password.isBlank(), "`LOGIN_PASSWORD` env is blank");
    }

    @Test(description = "Search Product using keyword 'Men Tshirt'")
    @Story("Positive Test - Search for existing product")
    @Owner("Igor")
    public void testSearchProduct() {
        Response response = apiRequestHelper.sendPostFormRequest(
                AUTOMATION_EXERCISE_URI + Endpoints.SEARCH_PRODUCT,
                "search_product",
                "Men Tshirt"
        );

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("Men Tshirt"), "Response body should contain 'Men Tshirt'");
    }

    @Test(description = "Verify Login with valid email and password")
    @Story("Positive Test - Verify existing user login")
    @Owner("Igor")
    public void testVerifyLogin() {
        Map<String, String> formData = new HashMap<>();
        formData.put("email", email);
        formData.put("password", password);

        Response response = apiRequestHelper.sendPostRequestWithFormData(
                AUTOMATION_EXERCISE_URI + Endpoints.VERIFY_LOGIN,
                formData
        );

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("User exists!"), "Response body should contain 'User exists!'");
    }

    @Test(description = "POST To Search Product without search_product parameter")
    @Story("API Validation")
    @Owner("Igor")
    public void testValidateResponseCodeFromBody() {
        Response response = apiRequestHelper.sendPostFormRequest(
                AUTOMATION_EXERCISE_URI + Endpoints.SEARCH_PRODUCT,
                "",
                ""
        );

        String body = response.asString();
        Map<String, Object> json = new Gson().fromJson(body, Map.class);

        int apiCode = ((Number) json.get("responseCode")).intValue();
        Assert.assertEquals(apiCode, 400, "Expected internal API responseCode = 400");

        Assert.assertTrue(body.contains("Bad request"), "Body should contain 'Bad request' message");
        Assert.assertEquals(response.getStatusCode(), 200, "HTTP status should always be 200 for this API");
    }
}
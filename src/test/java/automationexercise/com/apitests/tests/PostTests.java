package automationexercise.com.apitests.tests;

import automationexercise.com.apitests.BaseTest;
import automationexercise.com.apitests.services.Endpoints;
import automationexercise.com.apitests.helpers.ApiRequestHelper;
import automationexercise.com.utils.ConfigPaths;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static automationexercise.com.apitests.services.Endpoints.VERIFY_LOGIN;

public class PostTests extends BaseTest {

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

    @Test(description = "Search Product using keyword 'Men Tshirt'")
    @Story("Positive Test - Search for existing product")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    public void testSearchProduct() {
        Response response = apiRequestHelper.sendPostFormRequest(AUTOMATION_EXERCISE_URI + Endpoints.SEARCH_PRODUCT, "search_product", "Men Tshirt");

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("Men Tshirt"), "Response body should contain 'Men Tshirt'");

    }

    @Test(description = "Verify Login with valid email and password")
    @Story("Positive Test - Verify existing user login")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Igor")
    public void testVerifyLogin() {
        Map<String, String> formData = new HashMap<>();
        formData.put("email", email);
        formData.put("password", password);

        Response response = apiRequestHelper.sendPostRequestWithFormData(AUTOMATION_EXERCISE_URI + VERIFY_LOGIN, formData);
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getBody().asString().contains("User exists!"), "Response body should contain 'User exists!'");

    }

}

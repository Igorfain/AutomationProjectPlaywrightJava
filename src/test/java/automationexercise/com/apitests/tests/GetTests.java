package automationexercise.com.apitests.tests;

import automationexercise.com.apitests.BaseTest;
import automationexercise.com.apitests.services.ApiService;
import automationexercise.com.apitests.services.Endpoints;
import automationexercise.com.utils.ConfigPaths;
import com.google.gson.Gson;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class GetTests extends BaseTest {

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

    private final ApiService apiService = new ApiService();

    @Test(description = "Verify /api/productsList returns valid products list")
    @Story("Get All Products")
    @Severity(SeverityLevel.NORMAL)
    @Tag("API")
    @Owner("Igor")
    public void testGetAllProductList() {
        Response response = apiService.sendGetRequest(AUTOMATION_EXERCISE_URI + Endpoints.GET_ALL_PRODUCTS);
        Allure.addAttachment("Response Body", "application/json", response.asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Map<String, Object> json = new Gson().fromJson(response.asString(), Map.class);
        Assert.assertEquals(((Double) json.get("responseCode")).intValue(), 200);

        List<Map<String, Object>> products = (List<Map<String, Object>>) json.get("products");
        Assert.assertFalse(products.isEmpty());
        Assert.assertTrue(products.get(0).containsKey("name"));
    }

    @Test(description = "Verify that /api/brandsList returns valid brand list")
    @Story("Get All Brands")
    @Tag("API")
    @Owner("Igor")
    @Link(name = "API List", url = "https://automationexercise.com/api_list")
    public void testGetAllBrandsList() {

        Response response = apiService.sendGetRequest(AUTOMATION_EXERCISE_URI + Endpoints.GET_ALL_BRANDS);
        Assert.assertEquals(response.getStatusCode(), 200);
        Map<String, Object> json = new Gson().fromJson(response.asString(), Map.class);
        Assert.assertEquals(((Double) json.get("responseCode")).intValue(), 200);
        List<Map<String, Object>> brands = (List<Map<String, Object>>) json.get("brands");
        Assert.assertFalse(brands.isEmpty());
        Assert.assertTrue(brands.get(0).containsKey("brand"));
    }

    @Test(description = "Verify POST /api/productsList returns 405 in response body")
    @Story("Negative Test - Unsupported Method")
    @Tag("API")
    @Owner("Igor")
    public void testPostToProductsList_NotAllowed() {
        Response response = apiService.sendPostRequest(
                AUTOMATION_EXERCISE_URI + Endpoints.GET_ALL_PRODUCTS, "{}");

        Allure.addAttachment("Response Body", "application/json", response.asString());

        Map<String, Object> json = new Gson().fromJson(response.asString(), Map.class);
        Assert.assertEquals(((Double) json.get("responseCode")).intValue(), 405);
        Assert.assertTrue(json.get("message").toString().contains("not supported"));
    }

}

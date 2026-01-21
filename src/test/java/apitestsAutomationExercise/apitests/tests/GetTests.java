package apitestsAutomationExercise.apitests.tests;

import apitestsAutomationExercise.apitests.BaseApiTest;
import apitestsAutomationExercise.apitests.services.ApiService;
import apitestsAutomationExercise.apitests.services.Endpoints;
import com.google.gson.Gson;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

public class GetTests extends BaseApiTest {

    private final ApiService apiService = new ApiService();

    @Test(description = "Verify /api/productsList returns valid products list")
    @Story("Get All Products")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Igor")
    public void testGetAllProductList() {
        logTestStep();
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
    @Owner("Igor")
    @Link(name = "API List", url = "https://automationexercise.com/api_list")
    public void testGetAllBrandsList() {
        logTestStep();
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
    @Owner("Igor")
    public void testPostToProductsList_NotAllowed() {
        logTestStep();
        Response response = apiService.sendPostRequest(
                AUTOMATION_EXERCISE_URI + Endpoints.GET_ALL_PRODUCTS, "{}");

        Allure.addAttachment("Response Body", "application/json", response.asString());

        Map<String, Object> json = new Gson().fromJson(response.asString(), Map.class);
        Assert.assertEquals(((Double) json.get("responseCode")).intValue(), 405);
        Assert.assertTrue(json.get("message").toString().contains("not supported"));
    }

}

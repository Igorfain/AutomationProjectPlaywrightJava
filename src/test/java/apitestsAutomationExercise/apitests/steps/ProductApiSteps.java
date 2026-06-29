package apitestsAutomationExercise.apitests.steps;

import apitestsAutomationExercise.apitests.helpers.ApiRequestHelper;
import apitestsAutomationExercise.apitests.services.Endpoints;
import com.google.gson.Gson;
import common.infra.ConsoleReporter;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductApiSteps {

    private final ApiRequestHelper apiRequestHelper;
    private final String baseUri;

    public ProductApiSteps(String baseUri) {
        this.apiRequestHelper = new ApiRequestHelper();
        this.baseUri = baseUri;
    }

    @Step("Verify /api/productsList returns valid products list")
    public ProductApiSteps verifyProductsListReturnsValidList() {
        ConsoleReporter.log("Verify /api/productsList returns valid products list");
        Response productsListResponse = apiRequestHelper.sendGetRequest(baseUri + Endpoints.GET_ALL_PRODUCTS);
        Allure.addAttachment("Response Body", "application/json", productsListResponse.asString());

        int actualHttpStatus = productsListResponse.getStatusCode();
        Assert.assertEquals(actualHttpStatus, 200,
                "HTTP status mismatch. Expected: 200, Actual: " + actualHttpStatus);

        Map<String, Object> productsListJson = new Gson().fromJson(productsListResponse.asString(), Map.class);
        int actualProductsListApiResponseCode = ((Double) productsListJson.get("responseCode")).intValue();
        Assert.assertEquals(actualProductsListApiResponseCode, 200,
                "API responseCode mismatch. Expected: 200, Actual: " + actualProductsListApiResponseCode);

        List<Map<String, Object>> actualProducts = (List<Map<String, Object>>) productsListJson.get("products");
        Assert.assertFalse(actualProducts.isEmpty(),
                "Products list should not be empty. Expected: non-empty list, Actual: empty");
        Assert.assertTrue(actualProducts.get(0).containsKey("name"),
                "First product should contain 'name' field. Actual fields: " + actualProducts.get(0).keySet());
        return this;
    }

    @Step("Verify /api/brandsList returns valid brands list")
    public ProductApiSteps verifyBrandsListReturnsValidList() {
        ConsoleReporter.log("Verify /api/brandsList returns valid brands list");
        Response brandsListResponse = apiRequestHelper.sendGetRequest(baseUri + Endpoints.GET_ALL_BRANDS);

        int actualHttpStatus = brandsListResponse.getStatusCode();
        Assert.assertEquals(actualHttpStatus, 200,
                "HTTP status mismatch. Expected: 200, Actual: " + actualHttpStatus);

        Map<String, Object> brandsListJson = new Gson().fromJson(brandsListResponse.asString(), Map.class);
        int actualBrandsListApiResponseCode = ((Double) brandsListJson.get("responseCode")).intValue();
        Assert.assertEquals(actualBrandsListApiResponseCode, 200,
                "API responseCode mismatch. Expected: 200, Actual: " + actualBrandsListApiResponseCode);

        List<Map<String, Object>> actualBrands = (List<Map<String, Object>>) brandsListJson.get("brands");
        Assert.assertFalse(actualBrands.isEmpty(),
                "Brands list should not be empty. Expected: non-empty list, Actual: empty");
        Assert.assertTrue(actualBrands.get(0).containsKey("brand"),
                "First brand should contain 'brand' field. Actual fields: " + actualBrands.get(0).keySet());
        return this;
    }

    @Step("Verify POST /api/productsList returns 405 in response body")
    public ProductApiSteps verifyPostToProductsListIsNotAllowed() {
        ConsoleReporter.log("Verify POST /api/productsList returns 405 in response body");
        Response postToProductsListResponse = apiRequestHelper.sendPostRequest(baseUri + Endpoints.GET_ALL_PRODUCTS, "{}");
        Allure.addAttachment("Response Body", "application/json", postToProductsListResponse.asString());

        Map<String, Object> postToProductsListJson = new Gson().fromJson(postToProductsListResponse.asString(), Map.class);
        int actualPostNotAllowedResponseCode = ((Double) postToProductsListJson.get("responseCode")).intValue();
        Assert.assertEquals(actualPostNotAllowedResponseCode, 405,
                "API responseCode mismatch. Expected: 405, Actual: " + actualPostNotAllowedResponseCode);

        String actualNotAllowedMessage = postToProductsListJson.get("message").toString();
        Assert.assertTrue(actualNotAllowedMessage.contains("not supported"),
                "Message should contain 'not supported'. Actual: " + actualNotAllowedMessage);
        return this;
    }

    @Step("Verify search product returns results for keyword: {keyword}")
    public ProductApiSteps verifySearchProductReturnsResults(String keyword) {
        ConsoleReporter.log("Verify search product returns results for keyword: " + keyword);
        Response searchProductResponse = apiRequestHelper.sendPostFormRequest(
                baseUri + Endpoints.SEARCH_PRODUCT,
                "search_product",
                keyword
        );

        int actualHttpStatus = searchProductResponse.getStatusCode();
        Assert.assertEquals(actualHttpStatus, 200,
                "HTTP status mismatch. Expected: 200, Actual: " + actualHttpStatus);
        Assert.assertTrue(searchProductResponse.getBody().asString().contains(keyword),
                "Response body should contain '" + keyword + "'. Actual body: " + searchProductResponse.getBody().asString());
        return this;
    }

    @Step("Verify search product without parameter returns 400 responseCode")
    public ProductApiSteps verifySearchProductWithoutParamReturns400() {
        ConsoleReporter.log("Verify search product without parameter returns 400 responseCode");
        Response searchProductWithoutParamResponse = apiRequestHelper.sendPostRequestWithFormData(
                baseUri + Endpoints.SEARCH_PRODUCT,
                new HashMap<>()
        );

        String searchWithoutParamResponseBody = searchProductWithoutParamResponse.asString();
        Map<String, Object> searchProductWithoutParamJson = new Gson().fromJson(searchWithoutParamResponseBody, Map.class);

        int apiResponseCode = ((Number) searchProductWithoutParamJson.get("responseCode")).intValue();
        Assert.assertEquals(apiResponseCode, 400,
                "API responseCode mismatch. Expected: 400, Actual: " + apiResponseCode);
        Assert.assertTrue(searchWithoutParamResponseBody.contains("Bad request"),
                "Body should contain 'Bad request'. Actual: " + searchWithoutParamResponseBody);

        int actualHttpStatus = searchProductWithoutParamResponse.getStatusCode();
        Assert.assertEquals(actualHttpStatus, 200,
                "HTTP status mismatch. Expected: 200, Actual: " + actualHttpStatus);
        return this;
    }
}

package apitestsAutomationExercise.apitests.steps;

import apitestsAutomationExercise.apitests.helpers.ApiRequestHelper;
import apitestsAutomationExercise.apitests.services.Endpoints;
import com.google.gson.Gson;
import common.infra.ConsoleReporter;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

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

        Assert.assertEquals(productsListResponse.getStatusCode(), 200);

        Map<String, Object> productsListJson = new Gson().fromJson(productsListResponse.asString(), Map.class);
        Assert.assertEquals(((Double) productsListJson.get("responseCode")).intValue(), 200);

        List<Map<String, Object>> actualProducts = (List<Map<String, Object>>) productsListJson.get("products");
        Assert.assertFalse(actualProducts.isEmpty());
        Assert.assertTrue(actualProducts.get(0).containsKey("name"));
        return this;
    }

    @Step("Verify /api/brandsList returns valid brands list")
    public ProductApiSteps verifyBrandsListReturnsValidList() {
        ConsoleReporter.log("Verify /api/brandsList returns valid brands list");
        Response brandsListResponse = apiRequestHelper.sendGetRequest(baseUri + Endpoints.GET_ALL_BRANDS);

        Assert.assertEquals(brandsListResponse.getStatusCode(), 200);

        Map<String, Object> brandsListJson = new Gson().fromJson(brandsListResponse.asString(), Map.class);
        Assert.assertEquals(((Double) brandsListJson.get("responseCode")).intValue(), 200);

        List<Map<String, Object>> actualBrands = (List<Map<String, Object>>) brandsListJson.get("brands");
        Assert.assertFalse(actualBrands.isEmpty());
        Assert.assertTrue(actualBrands.get(0).containsKey("brand"));
        return this;
    }

    @Step("Verify POST /api/productsList returns 405 in response body")
    public ProductApiSteps verifyPostToProductsListIsNotAllowed() {
        ConsoleReporter.log("Verify POST /api/productsList returns 405 in response body");
        Response postToProductsListResponse = apiRequestHelper.sendPostRequest(baseUri + Endpoints.GET_ALL_PRODUCTS, "{}");
        Allure.addAttachment("Response Body", "application/json", postToProductsListResponse.asString());

        Map<String, Object> postToProductsListJson = new Gson().fromJson(postToProductsListResponse.asString(), Map.class);
        Assert.assertEquals(((Double) postToProductsListJson.get("responseCode")).intValue(), 405);
        Assert.assertTrue(postToProductsListJson.get("message").toString().contains("not supported"));
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

        Assert.assertEquals(searchProductResponse.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(searchProductResponse.getBody().asString().contains(keyword),
                "Response body should contain '" + keyword + "'");
        return this;
    }

    @Step("Verify search product without parameter returns 400 responseCode")
    public ProductApiSteps verifySearchProductWithoutParamReturns400() {
        ConsoleReporter.log("Verify search product without parameter returns 400 responseCode");
        Response searchProductWithoutParamResponse = apiRequestHelper.sendPostFormRequest(
                baseUri + Endpoints.SEARCH_PRODUCT,
                "",
                ""
        );

        String searchWithoutParamResponseBody = searchProductWithoutParamResponse.asString();
        Map<String, Object> searchProductWithoutParamJson = new Gson().fromJson(searchWithoutParamResponseBody, Map.class);

        int apiResponseCode = ((Number) searchProductWithoutParamJson.get("responseCode")).intValue();
        Assert.assertEquals(apiResponseCode, 400, "Expected internal API responseCode = 400");
        Assert.assertTrue(searchWithoutParamResponseBody.contains("Bad request"), "Body should contain 'Bad request' message");
        Assert.assertEquals(searchProductWithoutParamResponse.getStatusCode(), 200, "HTTP status should always be 200 for this API");
        return this;
    }
}

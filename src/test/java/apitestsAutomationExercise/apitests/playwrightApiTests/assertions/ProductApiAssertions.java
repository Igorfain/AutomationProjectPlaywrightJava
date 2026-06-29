package apitestsAutomationExercise.apitests.playwrightApiTests.assertions;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;
import org.testng.Assert;

public final class ProductApiAssertions {

    private ProductApiAssertions() {}

    // ---------- POSITIVE ----------

    public static void assertProductsListResponse(APIResponse response) {
        logResponse("GET /productsList", response);

        Assert.assertEquals(response.status(), 200, "HTTP status");

        JsonObject productsListResponseBody = JsonParser.parseString(response.text()).getAsJsonObject();
        Assert.assertEquals(productsListResponseBody.get("responseCode").getAsInt(), 200);
        Assert.assertTrue(productsListResponseBody.has("products"));
    }

    public static void assertSearchProductResponse(APIResponse response) {
        logResponse("POST /searchProduct", response);

        Assert.assertEquals(response.status(), 200, "HTTP status");

        JsonObject searchProductResponseBody = JsonParser.parseString(response.text()).getAsJsonObject();
        Assert.assertEquals(searchProductResponseBody.get("responseCode").getAsInt(), 200);
        Assert.assertTrue(searchProductResponseBody.has("products"));
    }

    // ---------- NEGATIVE ----------

    // Empty search -> API returns full list (bad API design, but expected)
    public static void assertEmptySearchReturnsAllProducts(APIResponse response) {
        logResponse("EMPTY SEARCH", response);

        Assert.assertEquals(response.status(), 200);

        JsonObject emptySearchResponseBody = JsonParser.parseString(response.text()).getAsJsonObject();
        Assert.assertEquals(emptySearchResponseBody.get("responseCode").getAsInt(), 200);
        Assert.assertTrue(emptySearchResponseBody.has("products"));
    }

    // Missing param -> logical error, HTTP still 200
    public static void assertMissingParamError(APIResponse response) {
        logResponse("MISSING PARAM", response);

        Assert.assertEquals(response.status(), 200);

        JsonObject missingParamResponseBody = JsonParser.parseString(response.text()).getAsJsonObject();
        Assert.assertEquals(missingParamResponseBody.get("responseCode").getAsInt(), 400);
        Assert.assertTrue(
                missingParamResponseBody.get("message").getAsString().toLowerCase().contains("missing")
        );
    }

    // ---------- LOGGING ----------

    private static void logResponse(String title, APIResponse response) {
        System.out.println("----- API RESPONSE -----");
        System.out.println("Endpoint: " + title);
        System.out.println("Status  : " + response.status());
        System.out.println("Body    : ");
        System.out.println(response.text());
        System.out.println("------------------------");
    }
}

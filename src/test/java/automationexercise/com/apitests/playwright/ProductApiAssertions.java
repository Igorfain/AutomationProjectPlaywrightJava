package automationexercise.com.apitests.playwright;

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

        JsonObject body = JsonParser.parseString(response.text()).getAsJsonObject();
        Assert.assertEquals(body.get("responseCode").getAsInt(), 200);
        Assert.assertTrue(body.has("products"));
    }

    public static void assertSearchProductResponse(APIResponse response) {
        logResponse("POST /searchProduct", response);

        Assert.assertEquals(response.status(), 200, "HTTP status");

        JsonObject body = JsonParser.parseString(response.text()).getAsJsonObject();
        Assert.assertEquals(body.get("responseCode").getAsInt(), 200);
        Assert.assertTrue(body.has("products"));
    }

    // ---------- NEGATIVE ----------

    // Empty search -> API returns full list (bad API design, but expected)
    public static void assertEmptySearchReturnsAllProducts(APIResponse response) {
        logResponse("EMPTY SEARCH", response);

        Assert.assertEquals(response.status(), 200);

        JsonObject body = JsonParser.parseString(response.text()).getAsJsonObject();
        Assert.assertEquals(body.get("responseCode").getAsInt(), 200);
        Assert.assertTrue(body.has("products"));
    }

    // Missing param -> logical error, HTTP still 200
    public static void assertMissingParamError(APIResponse response) {
        logResponse("MISSING PARAM", response);

        Assert.assertEquals(response.status(), 200);

        JsonObject body = JsonParser.parseString(response.text()).getAsJsonObject();
        Assert.assertEquals(body.get("responseCode").getAsInt(), 400);
        Assert.assertTrue(
                body.get("message").getAsString().toLowerCase().contains("missing")
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

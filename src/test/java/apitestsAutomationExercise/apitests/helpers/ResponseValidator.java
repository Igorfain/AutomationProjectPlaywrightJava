package apitestsAutomationExercise.apitests.helpers;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {

    /**
     * Validates response for the list of all products.
     *
     * @param response The Response object to validate.
     */
    public void validateAllProductsResponse(Response response) {
        System.out.println("All Products Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for retrieving all products");
        Assert.assertTrue(response.getBody().asString().contains("products"), "Response does not contain 'products'");
    }

    /**
     * Validates response for searching a product.
     *
     * @param response The Response object to validate.
     */
    public void validateSearchProductResponse(Response response) {
        System.out.println("Search Product Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for searching a product");
        Assert.assertTrue(response.getBody().asString().contains("product"), "Response does not contain 'product'");
    }

    /**
     * Validates response for viewing a single product detail.
     *
     * @param response The Response object to validate.
     */
    public void validateSingleProductDetailResponse(Response response) {
        System.out.println("Single Product Detail Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for viewing a single product");
        Assert.assertTrue(response.getBody().asString().contains("product"), "Response does not contain 'product'");
    }

    /**
     * Validates response for registering a new user.
     *
     * @param response The Response object to validate.
     */
    public void validateRegisterUserResponse(Response response) {
        System.out.println("Register User Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 for registering a user");
        Assert.assertTrue(response.getBody().asString().contains("success"), "Response does not contain 'success'");
    }

    /**
     * Validates response for verifying user login.
     *
     * @param response The Response object to validate.
     */
    public void validateLoginResponse(Response response) {
        System.out.println("Login Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for logging in");
        Assert.assertTrue(response.getBody().asString().contains("token"), "Response does not contain 'token'");
    }

    /**
     * Validates response for deleting a user account.
     *
     * @param response The Response object to validate.
     */
    public void validateDeleteUserAccountResponse(Response response) {
        System.out.println("Delete User Account Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for deleting user account");
        Assert.assertTrue(response.getBody().asString().contains("success"), "Response does not contain 'success'");
    }

    /**
     * Validates response for placing an order.
     *
     * @param response The Response object to validate.
     */
    public void validatePlaceOrderResponse(Response response) {
        System.out.println("Place Order Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for placing an order");
        Assert.assertTrue(response.getBody().asString().contains("orderId"), "Response does not contain 'orderId'");
    }
}

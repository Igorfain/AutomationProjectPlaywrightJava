package automationexercise.com.apitests.utils;

public class JsonUtils {

    /**
     * Creates a JSON request body for user registration.
     *
     * @param name     The name of the user.
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return JSON string for the request body.
     */
    public static String createRegisterUserRequestBody(String name, String email, String password) {
        return "{" +
                "\"name\": \"" + name + "\"," +
                "\"email\": \"" + email + "\"," +
                "\"password\": \"" + password + "\"" +
                "}";
    }

    /**
     * Creates a JSON request body for user login.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return JSON string for the request body.
     */
    public static String createLoginRequestBody(String email, String password) {
        return "{" +
                "\"email\": \"" + email + "\"," +
                "\"password\": \"" + password + "\"" +
                "}";
    }

    /**
     * Creates a JSON request body for placing an order.
     *
     * @param productId   The ID of the product.
     * @param quantity    The quantity of the product.
     * @param shippingAddress The shipping address.
     * @param paymentMethod   The payment method.
     * @return JSON string for the request body.
     */
    public static String createPlaceOrderRequestBody(String productId, int quantity, String shippingAddress, String paymentMethod) {
        return "{" +
                "\"productId\": \"" + productId + "\"," +
                "\"quantity\": " + quantity + "," +
                "\"shippingAddress\": \"" + shippingAddress + "\"," +
                "\"paymentMethod\": \"" + paymentMethod + "\"" +
                "}";
    }

    /**
     * Creates a JSON request body for adding an author.
     *
     * @param id      The ID of the author.
     * @param idBook  The ID of the book.
     * @param url     The author's URL.
     * @return JSON string for the request body.
     */
    public static String createAddAuthorRequestBody(int id, String idBook, String url) {
        return "{" +
                "\"id\": " + id + "," +
                "\"idBook\": \"" + idBook + "\"," +
                "\"url\": \"" + url + "\"" +
                "}";
    }

    /**
     * Creates a JSON request body for adding a product to the cart.
     *
     * @param productId The ID of the product.
     * @param quantity  The quantity of the product.
     * @return JSON string for the request body.
     */
    public static String createAddToCartRequestBody(String productId, int quantity) {
        return "{" +
                "\"productId\": \"" + productId + "\"," +
                "\"quantity\": " + quantity +
                "}";
    }
}

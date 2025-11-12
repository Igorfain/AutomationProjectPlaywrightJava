package automationexercise.com.apitests.services;

public class Endpoints {

    // Base path for API
    private static final String BASE_API = "/api";

    // User-related endpoints
    public static final String REGISTER_USER = BASE_API + "/registerUser";
    public static final String LOGIN_USER = BASE_API + "/login";
    public static final String DELETE_USER_ACCOUNT = BASE_API + "/deleteAccount";

    // Product-related endpoints
    public static final String GET_ALL_PRODUCTS = BASE_API + "/productsList";
    public static final String GET_ALL_BRANDS = "/api/brandsList";
    public static final String SEARCH_PRODUCT = BASE_API + "/searchProduct";
    public static String GET_PRODUCT_BY_ID(String id) {
        return BASE_API + "/productDetails/" + id;
    }

    // Cart-related endpoints
    public static final String VIEW_CART = BASE_API + "/viewCart";
    public static final String ADD_TO_CART = BASE_API + "/addToCart";
    public static final String DELETE_FROM_CART = BASE_API + "/deleteCart";

    // Order-related endpoints
    public static final String PLACE_ORDER = BASE_API + "/placeOrder";
    public static final String VIEW_ORDERS = BASE_API + "/viewOrders";
    public static String ORDER_DETAILS_BY_ID(String id) {
        return BASE_API + "/orderDetails/" + id;
    }

    // Payment-related endpoints
    public static final String PROCESS_PAYMENT = BASE_API + "/processPayment";

    // Utility endpoints
    public static final String CONTACT_US = BASE_API + "/contactUs";
}

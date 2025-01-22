package automationexercise.com.apitests.services;

public class Endpoints {

    // For Automation Exercise API

    // User-related endpoints
    public static final String REGISTER_USER = "/api/registerUser";
    public static final String LOGIN_USER = "/api/login";
    public static final String DELETE_USER_ACCOUNT = "/api/deleteAccount";

    // Product-related endpoints
    public static final String GET_ALL_PRODUCTS = "/api/productsList";
    public static String GET_PRODUCT_BY_ID(String id) {
        return "/api/productDetails/" + id;
    }
    public static final String SEARCH_PRODUCT = "/api/searchProduct";

    // Cart-related endpoints
    public static final String VIEW_CART = "/api/viewCart";
    public static final String ADD_TO_CART = "/api/addToCart";
    public static final String DELETE_FROM_CART = "/api/deleteCart";

    // Order-related endpoints
    public static final String PLACE_ORDER = "/api/placeOrder";
    public static final String VIEW_ORDERS = "/api/viewOrders";
    public static String ORDER_DETAILS_BY_ID(String id) {
        return "/api/orderDetails/" + id;
    }

    // Payment-related endpoints
    public static final String PROCESS_PAYMENT = "/api/processPayment";

    // Utility endpoints
    public static final String CONTACT_US = "/api/contactUs";

    // Extend with other endpoints as needed
}

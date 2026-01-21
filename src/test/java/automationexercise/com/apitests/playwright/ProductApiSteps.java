package automationexercise.com.apitests.playwright;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;

public class ProductApiSteps {

    private final APIRequestContext api;

    public ProductApiSteps(APIRequestContext api) {
        this.api = api;
    }

    public APIResponse getProductsList() {
        return api.get(ProductEndpoints.PRODUCTS_LIST);
    }

    public APIResponse searchProduct(String searchValue) {
        return api.post(
                ProductEndpoints.SEARCH_PRODUCT,
                RequestOptions.create()
                        .setForm(FormData.create()
                                .set("search_product", searchValue))
        );
    }

    public APIResponse searchProductEmpty() {
        return api.post(
                ProductEndpoints.SEARCH_PRODUCT,
                RequestOptions.create()
                        .setForm(FormData.create()
                                .set("search_product", ""))
        );
    }

    public APIResponse searchProductWithoutParam() {
        return api.post(ProductEndpoints.SEARCH_PRODUCT);
    }

}

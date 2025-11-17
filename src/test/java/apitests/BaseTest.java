package apitests;

import io.qameta.allure.Epic;
import io.qameta.allure.testng.Tag;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;

@Epic("API Tests")
@Tag("API Tests")
public abstract class BaseTest {

    protected final String JSON_PLACEHOLDER_URI = "https://jsonplaceholder.typicode.com";
    protected final String FAKE_REST_API_URI = "https://fakerestapi.azurewebsites.net";

    @BeforeClass
    public void setup() {

        // Enable request/response logging globally
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        // Optional default config (timeouts, encodings, etc.)
        RestAssured.useRelaxedHTTPSValidation();

    }
}

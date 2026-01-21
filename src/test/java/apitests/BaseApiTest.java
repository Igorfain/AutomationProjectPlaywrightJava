package apitests;

import io.qameta.allure.Epic;
import io.qameta.allure.testng.Tag;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import apitests.config.ApiMockConfig;
import apitests.mocks.MockService;
import common.infra.ConsoleReporter;

@Epic("API Tests")
@Tag("API Tests")
public abstract class BaseApiTest {

    protected ApiMockConfig apiConfig;
    protected MockService mock;

    protected final String JSON_PLACEHOLDER_URI = "https://jsonplaceholder.typicode.com";
    protected final String FAKE_REST_API_URI = "https://fakerestapi.azurewebsites.net";

    @BeforeClass
    public void setup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.useRelaxedHTTPSValidation();

        apiConfig = new ApiMockConfig();
        mock = new MockService(apiConfig.getMockUrl());
    }

    @BeforeMethod
    public void resetWiremockScenario() {
        RestAssured
                .given()
                .post(apiConfig.getMockUrl() + "/__admin/scenarios/reset")
                .then()
                .statusCode(200);
    }

    protected void logTestStep() {
        var result = Reporter.getCurrentTestResult();
        if (result == null) {
            ConsoleReporter.log("STEP: test started");
            return;
        }

        String description = result.getMethod().getDescription();
        String step = (description != null && !description.isBlank())
                ? description
                : result.getMethod().getMethodName();

        ConsoleReporter.log("STEP: " + step);
    }
}

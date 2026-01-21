package automationexercise.com.apitests;

import io.qameta.allure.Epic;
import io.qameta.allure.testng.Tag;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;

@Epic("API Tests")
@Epic("AutomationExercise API Tests")
@Tag("API Tests")
public abstract class BaseApiTest {

    protected final String AUTOMATION_EXERCISE_URI = "https://automationexercise.com";

    @BeforeClass
    public void setup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.useRelaxedHTTPSValidation();
    }
}

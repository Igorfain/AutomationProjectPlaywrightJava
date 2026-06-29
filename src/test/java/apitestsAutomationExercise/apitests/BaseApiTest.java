package apitestsAutomationExercise.apitests;

import common.infra.ConsoleReporter;
import io.qameta.allure.Epic;
import io.qameta.allure.testng.Tag;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;

@Epic("AutomationExercise API Tests")
@Tag("API Tests")
public abstract class BaseApiTest {

    protected final String AUTOMATION_EXERCISE_URI = "https://automationexercise.com";

    @BeforeClass
    public void setup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.useRelaxedHTTPSValidation();
    }

    protected void logTestStep() {
        var currentTestResult = org.testng.Reporter.getCurrentTestResult();

        if (currentTestResult == null) {
            ConsoleReporter.log("STEP: test started");
            return;
        }

        String description = currentTestResult.getMethod().getDescription();
        String step = (description != null && !description.isBlank())
                ? description
                : currentTestResult.getMethod().getMethodName();

        ConsoleReporter.log("STEP: " + step);
    }
}

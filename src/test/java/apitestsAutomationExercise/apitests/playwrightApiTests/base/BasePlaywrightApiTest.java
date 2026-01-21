package apitestsAutomationExercise.apitests.playwrightApiTests.base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import common.infra.ConsoleReporter;
import io.qameta.allure.Epic;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@Epic("AutomationExercise-PlaywrightAPI Tests")
@Tag("AutomationExercise-PlaywrightAPI")
public abstract class BasePlaywrightApiTest {

    protected Playwright playwright;
    protected APIRequestContext requestContext;

    protected static final String BASE_URL = "https://automationexercise.com";

    @BeforeClass
    public void setupApi() {
        playwright = Playwright.create();

        requestContext = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL(BASE_URL)
                        .setTimeout(20_000)
        );
    }

    @AfterClass
    public void tearDownApi() {
        if (requestContext != null) requestContext.dispose();
        if (playwright != null) playwright.close();
    }

    protected void logTestStep() {
        var result = org.testng.Reporter.getCurrentTestResult();

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

package automationexercise.com.apitests.playwrightApiTests.base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Epic;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@Epic("AI Tests")
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
}

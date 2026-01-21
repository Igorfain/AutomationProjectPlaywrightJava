package automationexercise.com.apitests.playwright;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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

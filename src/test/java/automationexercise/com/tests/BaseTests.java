package automationexercise.com.tests;

import com.microsoft.playwright.*;
import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.testng.Tag;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import automationexercise.com.steps.LoginSteps;
import automationexercise.com.utils.ConfigReader;
import automationexercise.com.utils.ConfigPaths;
import org.testng.annotations.Listeners;

import java.util.Map;

@Epic("UI Tests")
@Tag("UI Tests")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public abstract class BaseTests {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    protected String url;
    protected String username;
    protected String password;
    protected String invalidUsername;
    protected String invalidPassword;

    protected boolean doDefaultLogin() {
        return true;
    }


    @BeforeMethod
    public void setUp() {
        Map<String, Object> config = ConfigReader.readConfigFile(ConfigPaths.MAIN_CONFIG_PATH);
        loadCredentialsFromConfig(config);

        boolean headless = Boolean.parseBoolean(config.getOrDefault("headless", "true").toString());
        int slowMo = Integer.parseInt(config.getOrDefault("slowMo", "0").toString());
        int timeout = Integer.parseInt(config.getOrDefault("timeout", "10000").toString());
        int width = Integer.parseInt(config.getOrDefault("viewportWidth", "1920").toString());
        int height = Integer.parseInt(config.getOrDefault("viewportHeight", "1080").toString());

        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
                        .setSlowMo(slowMo)
        );

        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(width, height)
        );

        context.setDefaultTimeout(timeout);
        page = context.newPage();
        page.navigate(url);

        if (doDefaultLogin()) {
            new LoginSteps(page).login(username, password);
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            System.out.println("Test failed: " + result.getName());
            attachScreenshot();
        }

        // Clean up resources
        if (page != null && !page.isClosed()) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] attachScreenshot() {
        try {
            if (page != null) {
                // Capture and return screenshot as byte array
                return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            }
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
        return new byte[0]; // Return empty byte array if screenshot fails
    }

    private void loadCredentialsFromConfig(Map<String, Object> config) {
        url = (String) config.get("url");
        // Valid credentials
        username = ConfigReader.getEnv("LOGIN_USERNAME");
        password = ConfigReader.getEnv("LOGIN_PASSWORD");
        // Invalid credentials for negative tests
        invalidUsername = ConfigReader.getEnv("INVALID_USERNAME");
        invalidPassword = ConfigReader.getEnv("INVALID_PASSWORD");

        if (url == null || username == null || password == null) {
            throw new RuntimeException("Missing required credentials or URL. Check MainConfig.json and your .env file.");
        }
    }
}

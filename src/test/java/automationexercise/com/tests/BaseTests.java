package automationexercise.com.tests;

import com.microsoft.playwright.*;
//import io.qameta.allure.Step;
import io.qameta.allure.Attachment;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import automationexercise.com.steps.LoginSteps;
import automationexercise.com.utils.ConfigReader;
import automationexercise.com.utils.ConfigPaths;
import org.testng.annotations.Listeners;

import java.util.Map;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public abstract class BaseTests {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    protected String url;
    protected String username;
    protected String password;

    protected boolean doDefaultLogin() {
        return true;
    }


    @BeforeMethod
    public void setUp() {
        boolean headless = true;
        Map<String, Object> config = ConfigReader.readConfigFile(ConfigPaths.MAIN_CONFIG_PATH);
        loadCredentialsFromConfig(config);
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(500));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        context.setDefaultTimeout(10000);
        page = context.newPage();
        page.navigate(url);

        if (doDefaultLogin()) {
            LoginSteps loginSteps = new LoginSteps(page);
            loginSteps.login(username, password);
        }
    }

    //@Step("Navigate to the URL and perform default login")
   // public void navigateToUrlAndPerformDefaultLogin() {
   //     page.navigate(url);
    //    LoginSteps loginSteps = new LoginSteps(page);
    //    loginSteps.login(username, password);
 //   }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) { // Check if the test failed
            System.out.println("Test failed: " + result.getName());
            attachScreenshot(); // Attach screenshot to Allure report
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
        username = (String) config.get("username");
        password = (String) config.get("password");

        if (url == null || username == null || password == null) {
            throw new RuntimeException("Missing required credentials or URL in the configuration file.");
        }
    }
}

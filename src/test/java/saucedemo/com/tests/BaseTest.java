package saucedemo.com.tests;

import com.microsoft.playwright.*;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import saucedemo.com.steps.LoginSteps;
import saucedemo.com.utils.ConfigReader;
import saucedemo.com.utils.ConfigPaths;

import java.util.Map;

public abstract class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    protected String url;
    protected String username;
    protected String password;

    @BeforeMethod
    public void setUp() {

        Map<String, Object> config = ConfigReader.readConfigFile(ConfigPaths.MAIN_CONFIG_PATH);
        loadCredentialsFromConfig(config);

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        context.setDefaultTimeout(10000);
        page = context.newPage();
        navigateToUrlAndPerformDefaultLogin();
    }

    @Step("Navigate to the URL and perform default login")
    public void navigateToUrlAndPerformDefaultLogin() {
        page.navigate(url);
        LoginSteps loginSteps = new LoginSteps(page);
        loginSteps.login(username, password);
    }



    @AfterMethod
    public void tearDown() {
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

    private void loadCredentialsFromConfig(Map<String, Object> config) {
        url = (String) config.get("url");
        username = (String) config.get("username");
        password = (String) config.get("password");

        if (url == null || username == null || password == null) {
            throw new RuntimeException("Missing required credentials or URL in the configuration file.");
        }
    }
}

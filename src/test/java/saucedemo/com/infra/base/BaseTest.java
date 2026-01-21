package saucedemo.com.infra.base;

import com.microsoft.playwright.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.*;
import saucedemo.com.steps.LoginSteps;
import saucedemo.com.utils.ConfigReader;
import saucedemo.com.utils.ConfigPaths;

import java.util.Map;

@Epic("Saucedemo UI Tests")
@Tag("Saucedemo")
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

        boolean headless = Boolean.parseBoolean(config.getOrDefault("headless", "true").toString());
        int slowMo = Integer.parseInt(config.getOrDefault("slowMo", "0").toString());
        int timeout = Integer.parseInt(config.getOrDefault("timeout", "20000").toString());
        int width = Integer.parseInt(config.getOrDefault("viewportWidth", "1920").toString());
        int height = Integer.parseInt(config.getOrDefault("viewportHeight", "1080").toString());

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setSlowMo(slowMo));

        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(width, height));

        context.setDefaultTimeout(timeout);
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
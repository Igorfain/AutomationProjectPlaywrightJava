package saucedemo.com.tests;

import com.microsoft.playwright.*;
import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import saucedemo.com.steps.LoginSteps;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public abstract class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeMethod
    public void setUp() {
        Map<String, String> config = readConfigFile("src/test/java/saucedemo/com/infra/MainConfig.json");
        String url = config.get("url");
        String username = config.get("username");
        String password = config.get("password");

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(500));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        context.setDefaultTimeout(10000);
        page = context.newPage();
        page.navigate(url);

        login(username, password);
    }

    @Step("Login")
    public void login(String username, String password) {
        LoginSteps loginSteps = new LoginSteps(page);
        loginSteps.login(username, password);
        }

    @AfterMethod
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }

    private Map<String, String> readConfigFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read the configuration file.");
        }

    }
}

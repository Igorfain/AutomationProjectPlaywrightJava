package automationexercise.com.infra.base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.testng.Tag;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import automationexercise.com.steps.LoginSteps;
import automationexercise.com.utils.ConfigReader;
import automationexercise.com.utils.ConfigPaths;
import org.testng.annotations.Listeners;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Epic("AutomationExercise UI Tests")
@Tag("AutomationExercise")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public abstract class BaseTest {

    private static final Pattern CSRF_TOKEN_PATTERN =
            Pattern.compile("name=\"csrfmiddlewaretoken\" value=\"([^\"]+)\"");

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected String url;
    protected String username;
    protected String password;
    protected String invalidUsername;
    protected String invalidPassword;

    protected boolean doDefaultLogin() {
        return true;
    }

    protected boolean doApiLogin() {
        return false;
    }

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
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
                        .setSlowMo(slowMo)
        );

        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(width, height)
        );

        context.setDefaultTimeout(timeout);
        page = context.newPage();

        // Block ads
        page.route(
                "**/*google*",
                new Consumer<Route>() {
                    @Override
                    public void accept(Route route) {
                        String requestUrl = route.request().url();
                        if (requestUrl.contains("googlesyndication")
                                || requestUrl.contains("adservice.google")) {
                            route.abort();
                        } else {
                            route.resume();
                        }
                    }
                }
        );

        page.navigate(url);

        if (doApiLogin()) {
            performApiLogin();
            page.navigate(deriveHomeUrl());
        } else if (doDefaultLogin()) {
            new LoginSteps(page).login(username, password);
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            System.out.println("Test failed: " + result.getName());
            attachScreenshot("Failure Screenshot");
        }

        // Clean up resources
        if (page != null && !page.isClosed()) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    // Using Allure API directly as annotation might require AspectJ weaver
    public void attachScreenshot(String name) {
        try {
            if (page != null && !page.isClosed()) {
                byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
                Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
            }
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    private void loadCredentialsFromConfig(Map<String, Object> config) {
        url = (String) config.get("url");
        username = ConfigReader.getEnv("LOGIN_USERNAME");
        password = ConfigReader.getEnv("LOGIN_PASSWORD");
        invalidUsername = ConfigReader.getEnv("INVALID_USERNAME");
        invalidPassword = ConfigReader.getEnv("INVALID_PASSWORD");

        if (url == null || username == null || password == null) {
            throw new RuntimeException("Missing required credentials or URL. Check MainConfig.json and your .env file.");
        }
    }

    private void performApiLogin() {
        String csrfToken = getCsrfTokenFromLoginPage();

        APIResponse loginResponse = context.request().post(
                url,
                RequestOptions.create()
                        .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                        .setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                        .setHeader("Accept-Language", "en-US,en;q=0.9")
                        .setHeader("Origin", deriveHomeUrl())
                        .setHeader("Referer", url)
                        .setMaxRedirects(0)
                        .setForm(FormData.create()
                                .set("csrfmiddlewaretoken", csrfToken)
                                .set("email", username)
                                .set("password", password))
        );

        int actualStatusCode = loginResponse.status();
        loginResponse.dispose();

        if (actualStatusCode != 302) {
            throw new RuntimeException(
                    "API login failed. Expected status code: 302, Actual status code: "
                            + actualStatusCode
            );
        }
    }

    private String deriveHomeUrl() {
        URI loginUri = URI.create(url);
        return loginUri.getScheme() + "://" + loginUri.getHost();
    }

    private String getCsrfTokenFromLoginPage() {
        String loginPageContent = page.content();
        Matcher csrfTokenMatcher = CSRF_TOKEN_PATTERN.matcher(loginPageContent);

        if (!csrfTokenMatcher.find()) {
            throw new RuntimeException("CSRF token was not found on the login page.");
        }

        return csrfTokenMatcher.group(1);
    }
}

package saucedemo.com.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.com.infra.ConsoleReporter;
import saucedemo.com.steps.CartPageSteps;
import saucedemo.com.steps.LoginSteps;
import saucedemo.com.steps.ProductPageSteps;
import saucedemo.com.utils.ConfigReader;
import saucedemo.com.utils.ConfigPaths;

import java.util.Map;

public class DifferentUsersLoginTests extends BaseTest {

    private CartPageSteps cartPageSteps;
    private ProductPageSteps productPageSteps;
    private LoginSteps loginSteps;

    @BeforeMethod
    public void setUpTest() {
        productPageSteps = new ProductPageSteps(page);
        cartPageSteps = new CartPageSteps(page);
        loginSteps = new LoginSteps(page);
    }

    @Test(description = "Test login with problem user")
    public void testLoginWithProblemUser() {
        ConsoleReporter.log("Step 1 - Verify login with problem user");
        productPageSteps.logoutFromSite();
        Map<String, Object> config = ConfigReader.readConfigFile(ConfigPaths.MAIN_CONFIG_PATH);
        String problemUser = (String) config.get("problem_user");
        String problemUserPassword = (String) config.get("problem_user_password");
        loginSteps.login(problemUser, problemUserPassword);

    }
}

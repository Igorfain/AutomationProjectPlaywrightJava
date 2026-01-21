package saucedemo.com.tests;

import common.infra.ConsoleReporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.com.infra.base.BaseTest;
import saucedemo.com.steps.CartPageSteps;
import saucedemo.com.steps.LoginSteps;
import saucedemo.com.steps.ProductPageSteps;

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
        productPageSteps.logoutFromSite();
        String problemUser = dotenv.get("PROBLEM_USER");
        String problemUserPassword = dotenv.get("PROBLEM_USER_PASSWORD");
        loginSteps.login(problemUser, problemUserPassword);

    }
}

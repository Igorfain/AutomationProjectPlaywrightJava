package saucedemo.com.tests;

import common.infra.ConsoleReporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.com.infra.base.BaseTest;
import saucedemo.com.steps.CartPageSteps;
import saucedemo.com.steps.LoginSteps;
import saucedemo.com.steps.ProductPageSteps;

public class DifferentUsersLoginTests extends BaseTest {

    private ProductPageSteps productPageSteps;
    private LoginSteps loginSteps;
    private String problemUser;
    private String problemUserPassword;

    @BeforeMethod
    public void setUpTest() {
        productPageSteps = new ProductPageSteps(page);
        loginSteps = new LoginSteps(page);
        problemUser = dotenv.get("PROBLEM_USER");
        problemUserPassword = dotenv.get("PROBLEM_USER_PASSWORD");
    }

    @Test(description = "Test login with problem user")
    public void testLoginWithProblemUser() {

        productPageSteps.logoutFromSite();
        loginSteps.login(problemUser, problemUserPassword);

    }
}

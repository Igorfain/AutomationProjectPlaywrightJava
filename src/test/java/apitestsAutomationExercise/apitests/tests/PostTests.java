package apitestsAutomationExercise.apitests.tests;

import apitestsAutomationExercise.apitests.BaseApiTest;
import apitestsAutomationExercise.apitests.steps.ProductApiSteps;
import apitestsAutomationExercise.apitests.steps.UserApiSteps;
import automationexercise.com.utils.ConfigReader;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class PostTests extends BaseApiTest {

    private ProductApiSteps productApiSteps;
    private UserApiSteps userApiSteps;

    private String email;
    private String password;

    @BeforeClass
    public void readConfig() throws IOException {
        email = ConfigReader.getEnv("LOGIN_USERNAME");
        password = ConfigReader.getEnv("LOGIN_PASSWORD");

        Assert.assertNotNull(email, "`LOGIN_USERNAME` env is not set");
        Assert.assertFalse(email.isBlank(), "`LOGIN_USERNAME` env is blank");

        Assert.assertNotNull(password, "`LOGIN_PASSWORD` env is not set");
        Assert.assertFalse(password.isBlank(), "`LOGIN_PASSWORD` env is blank");
    }

    @BeforeClass(dependsOnMethods = "readConfig")
    public void initSteps() {
        productApiSteps = new ProductApiSteps(AUTOMATION_EXERCISE_URI);
        userApiSteps = new UserApiSteps(AUTOMATION_EXERCISE_URI);
    }

    @Test(description = "Search Product using keyword 'Men Tshirt'")
    @Owner("Igor")
    public void testSearchProduct() {
        productApiSteps.verifySearchProductReturnsResults("Men Tshirt");
    }

    @Test(description = "Verify Login with valid email and password")
    @Owner("Igor")
    public void testVerifyLogin() {
        userApiSteps.verifyLoginWithValidCredentials(email, password);
    }

    @Test(description = "POST To Search Product without search_product parameter")
    @Owner("Igor")
    public void testValidateResponseCodeFromBody() {
        productApiSteps.verifySearchProductWithoutParamReturns400();
    }
}

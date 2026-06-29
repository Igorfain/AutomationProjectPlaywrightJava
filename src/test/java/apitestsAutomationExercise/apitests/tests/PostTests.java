package apitestsAutomationExercise.apitests.tests;

import apitestsAutomationExercise.apitests.BaseApiTest;
import apitestsAutomationExercise.apitests.steps.ProductApiSteps;
import apitestsAutomationExercise.apitests.steps.UserApiSteps;
import automationexercise.com.utils.ConfigReader;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;

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

        if (email == null || email.isBlank()) {
            throw new IllegalStateException("`LOGIN_USERNAME` env is not set or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalStateException("`LOGIN_PASSWORD` env is not set or blank");
        }
    }

    @BeforeClass(dependsOnMethods = "readConfig")
    public void initSteps() {
        productApiSteps = new ProductApiSteps(AUTOMATION_EXERCISE_URI);
        userApiSteps = new UserApiSteps(AUTOMATION_EXERCISE_URI);
    }

    @Test(description = "Search Product using keyword 'Men Tshirt'")
    @Story("Product API")
    @Owner("Igor")
    public void testSearchProduct() {
        productApiSteps.verifySearchProductReturnsResults("Men Tshirt");
    }

    @Test(description = "Verify Login with valid email and password")
    @Story("User API")
    @Owner("Igor")
    public void testVerifyLogin() {
        userApiSteps.verifyLoginWithValidCredentials(email, password);
    }

    @Test(description = "POST To Search Product without search_product parameter")
    @Story("Product API")
    @Owner("Igor")
    public void testValidateResponseCodeFromBody() {
        productApiSteps.verifySearchProductWithoutParamReturns400();
    }
}

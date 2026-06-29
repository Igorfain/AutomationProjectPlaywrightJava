package apitestsAutomationExercise.apitests.tests;

import apitestsAutomationExercise.apitests.BaseApiTest;
import apitestsAutomationExercise.apitests.steps.UserApiSteps;
import automationexercise.com.utils.ConfigReader;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class PutTests extends BaseApiTest {

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
        userApiSteps = new UserApiSteps(AUTOMATION_EXERCISE_URI);
    }

    @Test(description = "Update user account details")
    @Story("User API")
    @Owner("Igor")
    public void testUpdateUserAccount() {
        userApiSteps.verifyUpdateUserAccountIsSuccessful(email, password);
    }
}

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
        userApiSteps = new UserApiSteps(AUTOMATION_EXERCISE_URI);
    }

    @Test(description = "Update user account details")
    @Owner("Igor")
    public void testUpdateUserAccount() {
        userApiSteps.verifyUpdateUserAccountIsSuccessful(email, password);
    }
}

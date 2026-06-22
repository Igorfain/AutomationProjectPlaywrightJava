package automationexercise.com.tests;

import automationexercise.com.infra.base.BaseTest;
import automationexercise.com.infra.testdata.RegistrationData;
import automationexercise.com.steps.LoginSteps;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Story("Registration")
public class RegistrationTests extends BaseTest {
    @Override
    protected boolean doDefaultLogin() { return false; } // without login

    private LoginSteps loginSteps;

    @BeforeMethod
    public void setUpTest() {
        loginSteps = new LoginSteps(page);
    }

    @Test(description = "Verify new user registration")
    public void registerNewUser() {
        String day = RegistrationData.getDay();
        String month = RegistrationData.getMonth();
        String year = RegistrationData.getYear();
        String state = RegistrationData.getState();
        String countryValue = RegistrationData.getCountryValue();
        loginSteps.registerNewUser(day,month,year,state,countryValue);
    }
}

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
    protected boolean doDefaultLogin() { return false; } // без логина

    private LoginSteps loginSteps;

    @BeforeMethod
    public void setUpTest() {
        loginSteps = new LoginSteps(page);

    }

    @Test(description = "Verify new user registration")
    public void registerNewUser() {
        RegistrationData registrationData = new RegistrationData();

        String genderType = registrationData.getGenderType();
        String day = registrationData.getDay();
        String month = registrationData.getMonth();
        String year = registrationData.getYear();
        String state = registrationData.getState();
        String countryValue = registrationData.getCountryValue();
        loginSteps.registerNewUser(genderType,day,month,year,state,countryValue);
    }
}

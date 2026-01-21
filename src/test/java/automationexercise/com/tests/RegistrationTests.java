package automationexercise.com.tests;

import automationexercise.com.infra.base.BaseTest;
import automationexercise.com.pages.LoginPage;
import automationexercise.com.steps.LoginSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class RegistrationTests extends BaseTest {
    @Override
    protected boolean doDefaultLogin() { return false; } // без логина

    private LoginSteps loginSteps;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpTest() {
        loginSteps = new LoginSteps(page);
        loginPage  = new LoginPage(page);
    }

    @Test(description = "Verify new user registration")
    public void registerNewUser() {
        var genderType= "#id_gender1";
        var day = "17";
        var month = "June";
        var year = "1993";
        var state = "South";
        var countryValue ="Israel";
        loginSteps.registerNewUser(genderType,day,month,year,state,countryValue);
    }
}

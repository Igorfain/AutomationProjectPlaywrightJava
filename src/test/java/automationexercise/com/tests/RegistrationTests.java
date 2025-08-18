package automationexercise.com.tests;

import automationexercise.com.pages.LoginPage;
import automationexercise.com.steps.LoginSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucedemo.com.infra.ConsoleReporter;

public class RegistrationTests extends BaseTests {
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
        ConsoleReporter.log("Verify new user registration");
        var genderType= "#id_gender1";
        var day = "5";
        var month = "June";
        var year = "1990";
        var state = "South";
        var countryValue ="Israel";
        loginSteps.registerNewUser(genderType,day,month,year,state,countryValue);
    }
}

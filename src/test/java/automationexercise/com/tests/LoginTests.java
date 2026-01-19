package automationexercise.com.tests;

import automationexercise.com.infra.ConsoleReporter;
import automationexercise.com.infra.base.BaseTest;
import automationexercise.com.steps.ContactUsSteps;
import automationexercise.com.steps.MainPageSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    private MainPageSteps mainPageSteps;
    private ContactUsSteps contactUsSteps;

    @BeforeMethod
    public void setUpTest() {
        mainPageSteps= new MainPageSteps(page);
        contactUsSteps=new ContactUsSteps(page);
    }

    @Test(description = "Verify login ")
    public void testLogin() {
        ConsoleReporter.log("Step 1 - Verify login in main page");
        String expectedUserText = "TestUser";
        mainPageSteps.verifyLoggedInUser(expectedUserText);
    }

    @Test (description = "Contact Us form verify")
    public void contactUsVerify(){
        var name = "Vasia Pupkin";
        var email = "pupkin@mail.com";
        var subject = "Automation feedback";
        var message = "Great site for test practice!";
        var filePath = "src/test/resources/test.txt";
      contactUsSteps
              .clickContactUsButton()
              .verifyGetInTouchText()
              .fillRelevantFields(name, email, subject, message)
              .uploadFile(filePath)
              .clickSubmitButton()
              .acceptAlert()
              .verifySuccessMessage()
              .clickHomeButton()
              .verifyNavigatedToHomePage();

    }

}

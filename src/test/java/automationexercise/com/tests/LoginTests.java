package automationexercise.com.tests;

import automationexercise.com.infra.base.BaseTest;
import automationexercise.com.infra.testdata.ContactUsData;
import automationexercise.com.steps.ContactUsSteps;
import automationexercise.com.steps.MainPageSteps;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Story("Login")
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
        
        String expectedUserText = "TestUser";
        mainPageSteps.verifyLoggedInUser(expectedUserText);
    }

    @Test (description = "Contact Us form verify")
    public void contactUsVerify() {

        String name = ContactUsData.getName();
        String email = ContactUsData.getEmail();
        String subject = ContactUsData.getSubject();
        String message = ContactUsData.getMessage();
        String filePath = ContactUsData.getFilePath();

        contactUsSteps
                .clickContactUsButton()
                .verifyGetInTouchText()
                .fillRelevantFields(name, email, subject, message)
                .uploadFile(filePath)
                .clickSubmitButton()
                .verifySuccessMessage()
                .clickHomeButton()
                .verifyNavigatedToHomePage();
    }

}

package automationexercise.com.apitests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    @BeforeClass
    public void setup() {
        // Base URI setup
        RestAssured.baseURI = "https://automationexercise.com";
    }
}

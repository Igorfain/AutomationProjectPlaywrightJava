package ApiTests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    @BeforeClass
    public void setup() {
        // Base URI setup
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
}

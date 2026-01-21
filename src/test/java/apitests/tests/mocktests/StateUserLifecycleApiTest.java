package apitests.tests.mocktests;

import apitests.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.com.infra.ConsoleReporter;

public class StateUserLifecycleApiTest extends BaseApiTest {

    @Test(description = "Verify the stateful lifecycle of a user: check existence, delete, and confirm non-existence")
    public void statefulUserLifecycle() {
        ConsoleReporter.log("STEP: Verify the stateful lifecycle of a user: check existence, delete, and confirm non-existence");

        Response getBefore = mock.get("/stateuser/123");
        Assert.assertEquals(getBefore.getStatusCode(), 200);

        Response deleteResp = mock.delete("/stateuser/delete/123");
        Assert.assertEquals(deleteResp.getStatusCode(), 200);

        Response getAfter = mock.get("/stateuser/123");
        Assert.assertEquals(getAfter.getStatusCode(), 404);
    }
}
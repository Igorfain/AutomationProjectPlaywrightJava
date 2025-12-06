package apitests.tests.mocktests;

import apitests.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StateUserLifecycleTest extends BaseTest {

    @Test
    public void statefulUserLifecycle() {

        Response getBefore = mock.get("/stateuser/123");
        Assert.assertEquals(getBefore.getStatusCode(), 200);

        Response deleteResp = mock.delete("/stateuser/delete/123");
        Assert.assertEquals(deleteResp.getStatusCode(), 200);

        Response getAfter = mock.get("/stateuser/123");
        Assert.assertEquals(getAfter.getStatusCode(), 404);

    }


}

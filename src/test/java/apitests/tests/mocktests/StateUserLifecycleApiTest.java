package apitests.tests.mocktests;

import apitests.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StateUserLifecycleApiTest extends BaseApiTest {

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

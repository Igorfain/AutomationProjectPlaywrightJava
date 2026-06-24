package automationexercise.com.infra.testdata;

import java.util.ArrayList;
import java.util.List;

public final class TopBarData {

    private TopBarData() {
    }

    public static List<String> getExpectedTopBarItemsWhenLoggedIn() {
        List<String> expectedTopBarItems = new ArrayList<>();
        expectedTopBarItems.add("Home");
        expectedTopBarItems.add("Products");
        expectedTopBarItems.add("Cart");
        expectedTopBarItems.add("Logout");
        expectedTopBarItems.add("Delete Account");
        expectedTopBarItems.add("Test Cases");
        expectedTopBarItems.add("API Testing");
        expectedTopBarItems.add("Video Tutorials");
        expectedTopBarItems.add("Contact us");
        expectedTopBarItems.add("Logged in as");
        return expectedTopBarItems;
    }
}

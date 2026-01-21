package common.infra;

import org.testng.ITestListener;
import org.testng.ITestResult;

//For API tests logging only ,with single step per test
public class TestsApiLogListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // Debug print to confirm the listener is active
        System.out.println("DEBUG: TestLogListener triggered for: " + result.getName());

        // Get description from @Test annotation
        String description = result.getMethod().getDescription();

        if (description != null && !description.isEmpty()) {
            // Use centralized common ConsoleReporter
            ConsoleReporter.log("STEP: " + description);
        } else {
            // Fallback to method name if description is missing
            ConsoleReporter.log("STEP: Running test " + result.getMethod().getMethodName());
        }
    }
}
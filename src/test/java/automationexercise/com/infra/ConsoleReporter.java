package automationexercise.com.infra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.qameta.allure.Allure;

public abstract class ConsoleReporter {

    // Method to log messages with a timestamp
    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("[HH:mm:ss] "));
        String logMessage = timestamp + message;

        // Log to console
        System.out.println(logMessage);

        // Log as a step in Allure report
        Allure.step(logMessage);

        // Optionally print to debug log
        if (isDebugMode()) {
            System.err.println(logMessage);
        }
    }

    // Method to check if debug mode is enabled
    private static boolean isDebugMode() {
        // You can customize this logic to check a system property or configuration
        return Boolean.getBoolean("debug");
    }
}

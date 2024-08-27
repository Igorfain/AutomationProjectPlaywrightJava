package saucedemo.com.infra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class ConsoleReporter {

    // Method to log messages with a timestamp
    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("[HH:mm:ss] "));
        String logMessage = timestamp + message;
        System.out.println(logMessage);

        // Optionally print to debug log (for example, during debugging)
        if (isDebugMode()) {
            System.err.println(logMessage);  // In Java, there's no direct equivalent to Debug.WriteLine, so using System.err for debug.
        }
    }

    // Method to check if debug mode is enabled
    private static boolean isDebugMode() {
        // You can customize this logic to check a system property or configuration
        return Boolean.getBoolean("debug");
    }
}

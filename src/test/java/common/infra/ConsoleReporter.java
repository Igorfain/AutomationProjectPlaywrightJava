package common.infra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleReporter {

    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(timestamp + " [INFO] " + message);
    }
}
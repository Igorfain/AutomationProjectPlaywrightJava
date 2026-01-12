package automationexercise.com.utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {

    // Added ignoreIfMissing() to allow execution in environments without a .env file (like Jenkins)
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static Map<String, Object> readConfigFile(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(content);

            Map<String, Object> config = new HashMap<>();
            for (String key : jsonObject.keySet()) {
                config.put(key, jsonObject.get(key));
            }
            return config;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the configuration file: " + filePath, e);
        }
    }

    public static String getEnv(String key) {
        // Check .env first, then fallback to System Environment variables (Jenkins Credentials)
        String value = dotenv.get(key);
        return (value != null) ? value : System.getenv(key);
    }
}
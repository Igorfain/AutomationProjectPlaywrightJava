package automationexercise.com.utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {


    private static final Dotenv dotenv = Dotenv.load();

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
        return dotenv.get(key);
    }
}
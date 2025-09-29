package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();
    
    
    // Load properties only once (singleton)

    static {
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties"))
        {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // Get property value by key
    
    public static String get(String key) {
        return properties.getProperty(key);
    }
}

package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {

    static Properties prop = new Properties();

    static { // static initializer
        try (InputStream file = DatabaseProperties.class.getClassLoader().getResourceAsStream("database.properties")) {
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}

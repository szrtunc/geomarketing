package driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties properties;
    static {
        String dosyaYolu="src/test/java/configuration.properties";

        try {
            FileInputStream  fis=new FileInputStream(dosyaYolu);
            properties=new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getProperty(String Key){
        return properties.getProperty(Key);
    }
    public static String[] getPropertyArray(String key) {
        String property = properties.getProperty(key);
        if (property != null) {
            return property.split(",");
        }
        return new String[0];
    }


}
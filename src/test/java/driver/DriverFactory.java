package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    //bildirimleri onaylamak için 1
                    ChromeOptions options = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.notifications", 1);
                    options.setExperimentalOption("prefs", prefs);
                    driver = new ChromeDriver(options);
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
        }
        return driver;
    }
    public static void quitDriver() {
        if (driver != null) {  //driver'a değer atanmışssa
            driver.quit();
            driver = null;
        }
    }


}

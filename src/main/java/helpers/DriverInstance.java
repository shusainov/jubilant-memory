package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverInstance {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setupRemoteDriver(String url, AbstractDriverOptions options) {
        try {
            driver = new RemoteWebDriver(new URL(url), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setupLocalDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
}

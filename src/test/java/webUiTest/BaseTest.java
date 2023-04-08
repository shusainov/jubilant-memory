package webUiTest;

import helpers.DriverInstance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    protected WebDriver driver;
    private boolean remote = true;

    @BeforeEach
    public void initDriver() {
        if (remote) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("se:name", "My simple test");
            DriverInstance.setupRemoteDriver("http://192.168.0.123:4444/", chromeOptions);
        } else {
            DriverInstance.setupLocalDriver();
        }
        driver = DriverInstance.getDriver();
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}

package webUiTest;

import helpers.DriverInstance;
import helpers.props.Props;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Props props = ConfigFactory.create(Props.class);

    @BeforeEach
    public void initDriver() {
        if (props.testRunMode().equals("remote")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("se:name", "My simple test");
            DriverInstance.setupRemoteDriver(props.remoteDriverUrl(), chromeOptions);
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

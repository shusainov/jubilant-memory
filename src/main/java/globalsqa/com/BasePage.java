package globalsqa.com;

import helpers.DriverInstance;
import io.qameta.allure.Step;
import net.bytebuddy.pool.TypePool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected static WebDriver driver = DriverInstance.getDriver();
    protected static long DEFAULT_WAIT_TIMEOUT_MS = 3000;

    @Step("Нажать кнопку {buttonName}")
    public static void clickButton(String buttonName) {
        String buttonLocator = "//button[normalize-space(text())='%s' and not(contains(@class,'tab'))]";
        WebElement button = (new WebDriverWait(driver, Duration.ofMillis(DEFAULT_WAIT_TIMEOUT_MS)))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(buttonLocator, buttonName))));
        button.click();
    }

    @Step("Нажать кнопку выбора вкладки {buttonName}")
    public static void clickTabButton(String buttonName) {
        String buttonLocator = "//button[normalize-space(text())='%s' and contains(@class,'tab')]";
        WebElement button = (new WebDriverWait(driver, Duration.ofMillis(DEFAULT_WAIT_TIMEOUT_MS)))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(buttonLocator, buttonName))));
        button.click();
    }

    @Step("Проверить наличие текста на странице")
    public static boolean checkTextExist(String text) {
        String locator = "//*[text()='%s']"; //слишком обобщённо, но думаю в данном случае допустимо
        WebElement textElement = (new WebDriverWait(driver, Duration.ofMillis(DEFAULT_WAIT_TIMEOUT_MS)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(locator, text))));
        return textElement.isDisplayed();
    }

    @Step("Нажать F5")
    public static void refresh() {
        driver.navigate().refresh();
    }

}

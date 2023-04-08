package globalsqa.com;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserPage extends BasePage {

    @Step("Вставить {text} в поле ввода")
    public static void writeToInputWithLabel(String labelText, String text) {
        String inputLocator = "//label[text()='%s']/following-sibling::input";
        WebElement input = (new WebDriverWait(driver, Duration.ofMillis(DEFAULT_WAIT_TIMEOUT_MS)))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(inputLocator, labelText))));
        input.sendKeys(text);
    }

    @Step("Получение значения {labelText}")
    public static String getFieldValue(String labelText) {
        String paramsFieldElementLocator = "//div[contains(text(),'Account Number')]";
        WebElement paramsEl = (new WebDriverWait(driver, Duration.ofMillis(DEFAULT_WAIT_TIMEOUT_MS)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(paramsFieldElementLocator)));

        String[] namesAndValues = paramsEl.getText().split("[:,]");
        for (int i = 0; i < namesAndValues.length; i++) {

            if (labelText.equals(namesAndValues[i].replaceAll("\\s", "")))
                return namesAndValues[i + 1].replaceAll("\\s", "");
        }
        Assertions.fail("Не найдено значение " + labelText);
        return "";
    }

}

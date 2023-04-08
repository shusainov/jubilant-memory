package globalsqa.com;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    @Step("Выбор пользователя {userName}")
    public static void selectUser(String userName) {
        String selectLocator = "//select";
        Select select = new Select(new WebDriverWait(driver, Duration.ofMillis(DEFAULT_WAIT_TIMEOUT_MS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(selectLocator))));
        select.selectByVisibleText(userName);
    }

}

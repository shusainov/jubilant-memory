package globalsqa.com;

import helpers.Other;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionsPage extends BasePage {

    public static int getTableRowsCount() {
        String tableLocator = "//table";
        WebElement tableEl = (new WebDriverWait(driver, Duration.ofMillis(DEFAULT_WAIT_TIMEOUT_MS)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableLocator)));
        String rowsLocator = "//tbody//tr";
        return tableEl.findElements(By.xpath(rowsLocator)).size();
    }

    public static List<Map<String, String>> getTableValues() {
        String tableLocator = "//table";
        WebElement tableEl = (new WebDriverWait(driver, Duration.ofMillis(DEFAULT_WAIT_TIMEOUT_MS)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableLocator)));

        String headersLocator = ".//thead//td";
        List<String> headers = tableEl.findElements(By.xpath(headersLocator)).stream()
                .map(WebElement::getText).collect(Collectors.toList());

        String rowsLocator = ".//tbody//tr";

        List<WebElement> rows = tableEl.findElements(By.xpath(rowsLocator));
        List<Map<String, String>> result = new ArrayList<>();
        String cellLocator = ".//td";
        for (WebElement row : rows) {
            List<WebElement> rowCells = row.findElements(By.xpath(cellLocator));
            Map<String, String> rowValues = new HashMap<>();
            for (int j = 0; j < headers.size(); j++) {
                rowValues.put(headers.get(j), rowCells.get(j).getText());
            }
            result.add(rowValues);
        }

        return result;
    }
}

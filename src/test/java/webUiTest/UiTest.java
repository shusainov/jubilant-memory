package webUiTest;

import globalsqa.com.LoginPage;
import globalsqa.com.StartPage;
import globalsqa.com.TransactionsPage;
import globalsqa.com.UserPage;
import helpers.CustomDate;
import helpers.CustomFile;
import helpers.Other;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static helpers.CustomFile.getFileInputStream;

public class UiTest extends BaseTest {

    @Test
    void testName() {
        StartPage.open();
        StartPage.clickButton("Customer Login");

        LoginPage.selectUser("Harry Potter");
        LoginPage.clickButton("Login");
        int currentDay = CustomDate.getCurrentDayValue();
        String summ = String.valueOf(Other.fibonacci(currentDay));
        Allure.addAttachment("Текущий день месяца", String.valueOf(currentDay));
        Allure.addAttachment("Сумма", summ);

        UserPage.clickTabButton("Deposit");
        UserPage.writeToInputWithLabel("Amount to be Deposited :", summ);
        UserPage.clickButton("Deposit");
        Assertions.assertTrue(UserPage.checkTextExist("Deposit Successful"));

        UserPage.clickTabButton("Withdrawl");
        UserPage.writeToInputWithLabel("Amount to be Withdrawn :", summ);
        UserPage.clickButton("Withdraw");
        Assertions.assertTrue(UserPage.checkTextExist("Transaction successful"));

        Assertions.assertEquals("0", UserPage.getFieldValue("Balance"));

        UserPage.clickTabButton("Transactions");
        //Странная табличка, не стабильный кусок
        String currentURL = driver.getCurrentUrl();
        Other.waitTillTrue(() -> !driver.getCurrentUrl().equals(currentURL), 1000, 20 * 1000);
        Other.waitTillTrue(() -> {
            TransactionsPage.refresh();
            return TransactionsPage.getTableRowsCount() > 0;
        }, 5 * 1000, 60 * 1000);

        List<Map<String, String>> table = TransactionsPage.getTableValues();
        boolean isWithdrawExist = table.stream().anyMatch(m -> m.get("Amount").equals(summ) && m.get("Transaction Type").equals("Debit"));
        boolean isDepositExist = table.stream().anyMatch(m -> m.get("Amount").equals(summ) && m.get("Transaction Type").equals("Credit"));
        Assertions.assertTrue(isWithdrawExist);
        Assertions.assertTrue(isDepositExist);

        String filePath = "csvFile.csv";
        CustomFile.writeTableValuesToFile(filePath, Other.convertDatesOnColumn(table, "Date-Time",
                "MMM d, yyyy hh:mm:ss a", "dd MMMM yyyy HH:mm:ss",
                new Locale("en", "US")));

        Allure.addAttachment("сsv файл", getFileInputStream(filePath));
    }
}

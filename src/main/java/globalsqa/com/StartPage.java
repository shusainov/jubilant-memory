package globalsqa.com;

import io.qameta.allure.Step;

public class StartPage extends BasePage {

    @Step("Открыть стартовую страницу")
    public static void open() {
        driver.get(props.startPageUrl());
    }
}

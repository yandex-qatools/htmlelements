package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;
import org.openqa.selenium.WebDriver;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.02.13
 */
public class RefreshPageAction implements Action {
    private final WebDriver driver;

    public static RefreshPageAction pageRefresh(WebDriver driver) {
        return new RefreshPageAction(driver);
    }

    public RefreshPageAction(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void perform() {
        driver.navigate().refresh();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("page refresh");
    }
}

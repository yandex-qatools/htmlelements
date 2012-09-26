package ru.yandex.qatools.htmlelements.matchers;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.matchers.mockfactory.MockFactory;
import ru.yandex.qatools.htmlelements.matchers.testelements.CustomBlock;
import ru.yandex.qatools.htmlelements.matchers.testpages.PageWithCustomBlock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 24.09.12
 */
public class IsElementDisplayedTest {
    private WebDriver driver = MockFactory.mockDriver();

    @Test
    public void blockIsDisplayed() {
        WebElement element = MockFactory.mockDisplayedElement(By.className(CustomBlock.CLASS_NAME), driver);
        PageWithCustomBlock page = new PageWithCustomBlock(driver);

        assertThat(element, isDisplayed());
        assertThat(page.getBlock(), isDisplayed());
    }

    @Test
    public void blockIsNotDisplayed() {
        WebElement element = MockFactory.mockNotDisplayedElement(By.className(CustomBlock.CLASS_NAME), driver);
        PageWithCustomBlock page = new PageWithCustomBlock(driver);

        assertThat(element, not(isDisplayed()));
        assertThat(page.getBlock(), not(isDisplayed()));
    }
}

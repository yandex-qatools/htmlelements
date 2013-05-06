package ru.yandex.qatools.htmlelements.thucydides;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 6:13 PM
 */
public class BlockPageTest {

    WebDriver driver = mock(WebDriver.class);

    @Before
    public void initDriver() {
        WebElement blockElement = mock(WebElement.class);
        WebElement innerElement = mock(WebElement.class);
        when(blockElement.findElement(By.className("element"))).thenReturn(innerElement);
        when(driver.findElement(By.className("block"))).thenReturn(blockElement);
    }

    @Test
    public void blockPageWithBlockElementShouldInitialise() {
        SomePage page = new SomePage(driver);
        assertThat(page, not(nullValue()));
        assertThat(page.blockElement, not(nullValue()));
        assertThat(page.blockElement.innerElement, not(nullValue()));
    }
}

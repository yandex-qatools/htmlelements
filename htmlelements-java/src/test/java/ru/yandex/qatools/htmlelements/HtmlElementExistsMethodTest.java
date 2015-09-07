package ru.yandex.qatools.htmlelements;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Kedrik alkedr@yandex-team.ru
 *         Date: 07.09.15
 */
public class HtmlElementExistsMethodTest {
    private final WebElement webElement = mock(WebElement.class);
    private final HtmlElement htmlElement = new HtmlElement();

    @Before
    public void setUp() {
        htmlElement.setWrappedElement(webElement);
    }

    @Test
    public void existsShouldReturnTrueIfIsDisplayedReturnsTrue() {
        when(webElement.isDisplayed()).thenReturn(true);
        assertThat(htmlElement.exists(), is(true));
    }

    @Test
    public void existsShouldReturnTrueIfIsDisplayedReturnsFalse() {
        when(webElement.isDisplayed()).thenReturn(false);
        assertThat(htmlElement.exists(), is(true));
    }

    @Test
    public void existsShouldReturnFalseIfIsDisplayedThrowsNoSuchElementException() {
        doThrow(new NoSuchElementException("")).when(webElement).isDisplayed();
        assertThat(htmlElement.exists(), is(false));
    }

    @Test(expected = TimeoutException.class)
    public void existsShouldCatchOnlyNoSuchElementException() {
        doThrow(new TimeoutException("")).when(webElement).isDisplayed();
        htmlElement.exists();
    }
}

package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Kedrik alkedr@yandex-team.ru
 *         Date: 07.09.15
 */
public class TypifiedElementExistsMethodTest {
    private final WebElement webElement = mock(WebElement.class);
    private final TypifiedElement typifiedElement = new TypifiedElement(webElement) {};

    @Test
    public void existsShouldReturnTrueIfIsDisplayedReturnsTrue() {
        when(webElement.isDisplayed()).thenReturn(true);
        assertThat(typifiedElement.exists(), is(true));
    }

    @Test
    public void existsShouldReturnTrueIfIsDisplayedReturnsFalse() {
        when(webElement.isDisplayed()).thenReturn(false);
        assertThat(typifiedElement.exists(), is(true));
    }

    @Test
    public void existsShouldReturnFalseIfIsDisplayedThrowsNoSuchElementException() {
        doThrow(new NoSuchElementException("")).when(webElement).isDisplayed();
        assertThat(typifiedElement.exists(), is(false));
    }

    @Test(expected = TimeoutException.class)
    public void existsShouldCatchOnlyNoSuchElementException() {
        doThrow(new TimeoutException("")).when(webElement).isDisplayed();
        typifiedElement.exists();
    }
}

package ru.yandex.qatools.htmlelements.matchers;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author eoff
 * Date: 13.03.13
 *
 * If we override for example getText() method in HtmlElement, then matcher hasText()
 * from WebElementMatchers and from WrapsElementMatchers will work differently, that seems
 * to be not an expected behaviour
 */

public class HtmlElementWorkWithMatcherTest {
    private static final String TEXT = "text";
    private static final String ERROR_TEXT = "error text";

    @Test
    public void shouldSeeThatHasTextMatchersWorkTheSameWay() {
        HtmlElement element = mock(HtmlElement.class);
        WebElement wrappedElement = mock(WebElement.class);

        when(element.getText()).thenReturn(TEXT);
        when(element.getWrappedElement()).thenReturn(wrappedElement);
        when(wrappedElement.getText()).thenReturn(ERROR_TEXT);

        assertThat(element, WebElementMatchers.hasText(TEXT));
        assertThat(element, WrapsElementMatchers.hasText(TEXT));
    }
}
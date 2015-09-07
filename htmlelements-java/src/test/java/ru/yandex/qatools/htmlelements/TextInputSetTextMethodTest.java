package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Kedrik alkedr@yandex-team.ru
 *         Date: 08.09.15
 */
public class TextInputSetTextMethodTest {
    private final WebElement webElement = mock(WebElement.class);
    private final TextInput textInput = new TextInput(webElement);

    @Test
    public void setTextShouldNotAddClearKeysForTextInputWithEmptyText() {
        when(webElement.getAttribute("value")).thenReturn("");
        textInput.setText("qwerty");
        verify(webElement).sendKeys("qwerty");
    }

    @Test
    public void setTextShouldAddClearKeysForTextInputWithNonEmptyText() {
        when(webElement.getAttribute("value")).thenReturn("12");
        textInput.setText("qwerty");
        verify(webElement).sendKeys(Keys.DELETE.toString() + Keys.BACK_SPACE + Keys.DELETE + Keys.BACK_SPACE + "qwerty");
    }
}

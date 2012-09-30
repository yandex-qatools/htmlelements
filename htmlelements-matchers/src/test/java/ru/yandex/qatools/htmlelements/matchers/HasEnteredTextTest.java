package ru.yandex.qatools.htmlelements.matchers;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.matchers.mockfactory.MockFactory;

import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.TypifiedElementMatchers.hasEnteredText;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 30.09.12
 */
public class HasEnteredTextTest {
    private static final String ENTERED_TEXT = "some text";

    @Test
    public void hasEnteredTextTest() {
        TextInput textInput = MockFactory.mockTextInput(ENTERED_TEXT);
        assertThat(textInput, hasEnteredText(ENTERED_TEXT));
    }
}

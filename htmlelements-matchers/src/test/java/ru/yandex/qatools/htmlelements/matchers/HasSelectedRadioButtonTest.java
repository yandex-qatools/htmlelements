package ru.yandex.qatools.htmlelements.matchers;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.element.Radio;
import ru.yandex.qatools.htmlelements.matchers.mockfactory.MockFactory;

import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.TypifiedElementMatchers.hasSelectedRadioButton;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasValue;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 30.09.12
 */
public class HasSelectedRadioButtonTest {
    private static final String SELECTED_BUTTON_VALUE = "some value";

    @Test
    public void hasSelectedOptionTest() {
        Radio radio = MockFactory.mockRadio(SELECTED_BUTTON_VALUE);
        assertThat(radio, hasSelectedRadioButton(hasValue(SELECTED_BUTTON_VALUE)));
    }
}

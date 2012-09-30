package ru.yandex.qatools.htmlelements.matchers;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.matchers.mockfactory.MockFactory;

import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.TypifiedElementMatchers.hasSelectedOption;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasValue;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 30.09.12
 */
public class HasSelectedOptionTest {
    private static final String SELECTED_OPTION_VALUE = "some value";

    @Test
    public void hasSelectedOptionTest() {
        Select select = MockFactory.mockSelect(SELECTED_OPTION_VALUE);
        assertThat(select, hasSelectedOption(hasValue(SELECTED_OPTION_VALUE)));
    }
}

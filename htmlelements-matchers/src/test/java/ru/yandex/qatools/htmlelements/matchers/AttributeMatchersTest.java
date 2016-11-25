package ru.yandex.qatools.htmlelements.matchers;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.matchers.testpages.PageWithAttributedElements;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.checked;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.focused;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.selected;

/**
 * @author Artem Koshelev art.koshelev@gmail.com
 *         Date: 12.04.16.
 */
public class AttributeMatchersTest {
    private PageWithAttributedElements page = new PageWithAttributedElements();

    @Test
    public void focusedTest() {
        assertThat(page.focused, focused());
    }

    @Test
    public void notFocusedTest() {
        assertThat(page.empty, not(focused()));
    }

    @Test
    public void selectedTest() {
        assertThat(page.selected, selected());
    }

    @Test
    public void notSelectedTest() {
        assertThat(page.empty, not(selected()));
    }

    @Test
    public void checkedTest() {
        assertThat(page.checked, checked());
    }

    @Test
    public void notCheckedTest() {
        assertThat(page.empty, not(checked()));
    }
}

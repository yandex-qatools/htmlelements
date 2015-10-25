package ru.yandex.qatools.htmlelements.matchers;

import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import ru.yandex.qatools.htmlelements.matchers.testpages.PageWithCheckBox;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static ru.yandex.qatools.htmlelements.matchers.TypifiedElementMatchers.isSelected;

/**
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
public class IsCheckedTest {
    @Test
    public void isChecked() {
        PageWithCheckBox page = new PageWithCheckBox();
        assertThat(page.variantA, isSelected());
    }

    @Test
    public void isUnChecked() {
        PageWithCheckBox page = new PageWithCheckBox();
        assertThat(page.variantB, not(isSelected()));
    }

    @Test(expected = NoSuchElementException.class)
    public void exceptionIfMissed() {
        PageWithCheckBox page = new PageWithCheckBox();
        assertThat(page.allOfThis, isSelected());
    }
}
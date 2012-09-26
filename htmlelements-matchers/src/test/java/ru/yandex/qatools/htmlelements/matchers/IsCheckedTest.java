package ru.yandex.qatools.htmlelements.matchers;

import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import ru.yandex.qatools.htmlelements.matchers.testpages.WithCheckBoxPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static ru.yandex.qatools.htmlelements.matchers.TypifiedElementMatchers.isSelected;

public class IsCheckedTest {
    @Test
    public void isChecked() {
        WithCheckBoxPage wcbp = new WithCheckBoxPage();
        assertThat(wcbp.variantA, isSelected());
    }

    @Test
    public void isUnChecked() {
        WithCheckBoxPage wcbp = new WithCheckBoxPage();
        assertThat(wcbp.variantB, not(isSelected()));
    }

    @Test(expected = NoSuchElementException.class)
    public void exceptionIfMisssed() {
        WithCheckBoxPage wcbp = new WithCheckBoxPage();
        assertThat(wcbp.allOfThis, isSelected());
    }
}
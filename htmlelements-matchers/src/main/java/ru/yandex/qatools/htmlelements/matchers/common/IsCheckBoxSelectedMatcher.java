package ru.yandex.qatools.htmlelements.matchers.common;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import ru.yandex.qatools.htmlelements.element.CheckBox;

/**
 * Indicates whether {@link CheckBox} is checked or not.
 *
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
public class IsCheckBoxSelectedMatcher extends TypeSafeMatcher<CheckBox> {
    @Override
    public boolean matchesSafely(CheckBox checkBox) {
        return checkBox.isSelected();
    }

    @Override
    public void describeMismatchSafely(CheckBox checkBox, Description description) {
        description.appendText(String.format("checkbox '%s' is not checked", checkBox));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("checkbox is checked");
    }

    /**
     * Creates matcher that tests if {@link CheckBox} is checked.
     */
    @Factory
    public static IsCheckBoxSelectedMatcher isSelected() {
        return new IsCheckBoxSelectedMatcher();
    }
}
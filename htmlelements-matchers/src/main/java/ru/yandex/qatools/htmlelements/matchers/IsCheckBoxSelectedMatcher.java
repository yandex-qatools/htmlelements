package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import ru.yandex.qatools.htmlelements.element.CheckBox;

public class IsCheckBoxSelectedMatcher extends TypeSafeMatcher<CheckBox> {
    @Override
    public boolean matchesSafely(CheckBox checkBox) {
        return checkBox.isSelected();
    }

    @Override
    public void describeMismatchSafely(CheckBox checkBox, Description description) {
        description.appendText(String.format("checkbox '%1$s' is not checked", checkBox));
    }

	@Override
	public void describeTo(Description description) {
		description.appendText("checkbox is checked");
	}

    @Factory
    public static IsCheckBoxSelectedMatcher isSelected() {
        return new IsCheckBoxSelectedMatcher();
    }
}
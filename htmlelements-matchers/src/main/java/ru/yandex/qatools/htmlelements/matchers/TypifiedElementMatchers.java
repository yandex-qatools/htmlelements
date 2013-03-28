package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Radio;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.matchers.common.HasEnteredTextMatcher;
import ru.yandex.qatools.htmlelements.matchers.common.HasSelectedOptionMatcher;
import ru.yandex.qatools.htmlelements.matchers.common.HasSelectedRadioButtonMatcher;
import ru.yandex.qatools.htmlelements.matchers.common.IsCheckBoxSelectedMatcher;

/**
 * Contains <a href="http://code.google.com/p/hamcrest/">matchers</a> for typified elements
 * (i.e. {@link CheckBox}, {@link Select}, {@link Radio} and {@link TextInput}).
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 25.09.12
 */
public final class TypifiedElementMatchers {

    private TypifiedElementMatchers() {
    }

    /**
     * Creates matcher that tests if {@link CheckBox} is checked.
     */
    public static Matcher<CheckBox> isSelected() {
        return IsCheckBoxSelectedMatcher.isSelected();
    }

    /**
     * Creates matcher that tests if {@link Select} has selected option matching the specified matcher.
     *
     * @param optionMatcher Matcher to match selected option with.
     */
    public static Matcher<Select> hasSelectedOption(Matcher<WebElement> optionMatcher) {
        return HasSelectedOptionMatcher.hasSelectedOption(optionMatcher);
    }

    /**
     * Creates matcher that tests if {@link Radio} has selected button matching the specified matcher.
     *
     * @param buttonMatcher Matcher to match selected radio button with.
     */
    public static Matcher<Radio> hasSelectedRadioButton(Matcher<WebElement> buttonMatcher) {
        return HasSelectedRadioButtonMatcher.hasSelectedRadioButton(buttonMatcher);
    }

    /**
     * Creates matcher that matches text entered in {@link TextInput} with specified matcher.
     *
     * @param textMatcher Matcher to match entered text with.
     */
    public static Matcher<TextInput> hasEnteredText(Matcher<String> textMatcher) {
        return HasEnteredTextMatcher.hasEnteredText(textMatcher);
    }

    /**
     * Creates matcher that checks if text entered in {@link TextInput} equals to given text.
     *
     * @param text Expected entered text.
     */
    public static Matcher<TextInput> hasEnteredText(String text) {
        return HasEnteredTextMatcher.hasEnteredText(text);
    }
}

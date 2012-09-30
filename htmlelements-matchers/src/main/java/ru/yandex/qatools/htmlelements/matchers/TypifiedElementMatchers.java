package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Radio;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Contains <a href="http://code.google.com/p/hamcrest/">matchers</a> for typified elements
 * (i.e. {@link CheckBox}, {@link Select}, {@link Radio} and {@link TextInput}).
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 25.09.12
 */
public class TypifiedElementMatchers {
    /**
     * Creates matcher that tests if {@link CheckBox} is checked.
     */
    public static Matcher<CheckBox> isSelected() {
        return IsCheckBoxSelectedMatcher.isSelected();
    }

    /**
     * Creates matcher that tests if {@link Select} has selected option matching the specified matcher.
     * @param optionMatcher
     */
    public static Matcher<Select> hasSelectedOption(Matcher<WebElement> optionMatcher) {
        return HasSelectedOptionMatcher.hasSelectedOption(optionMatcher);
    }

    /**
     * Creates matcher that tests if {@link Radio} has selected button matching the specified matcher.
     * @param buttonMatcher
     */
    public static Matcher<Radio> hasSelectedRadioButton(Matcher<WebElement> buttonMatcher) {
        return HasSelectedRadioButtonMatcher.hasSelectedRadioButton(buttonMatcher);
    }

    /**
     * Creates matcher that matches entered in {@link TextInput} text with specified matcher.
     * @param textMatcher
     */
    public static Matcher<TextInput> hasEnteredText(Matcher<String> textMatcher) {
        return HasEnteredTextMatcher.hasEnteredText(textMatcher);
    }

    public static Matcher<TextInput> hasEnteredText(String text) {
        return HasEnteredTextMatcher.hasEnteredText(text);
    }
}

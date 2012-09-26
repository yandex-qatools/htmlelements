package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Radio;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 25.09.12
 */
public class TypifiedElementMatchers {
    public static Matcher<CheckBox> isSelected() {
        return IsCheckBoxSelectedMatcher.isSelected();
    }

    public static Matcher<Select> hasSelectedOption(Matcher<WebElement> optionMatcher) {
        return HasSelectedOptionMatcher.hasSelectedOption(optionMatcher);
    }

    public static Matcher<Radio> hasSelectedRadioButton(Matcher<WebElement> buttonMatcher) {
        return HasSelectedRadioButtonMatcher.hasSelectedRadioButton(buttonMatcher);
    }

    public static Matcher<TextInput> hasEnteredText(Matcher<String> textMatcher) {
        return HasEnteredTextMatcher.hasEnteredText(textMatcher);
    }
}

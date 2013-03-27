package ru.yandex.qatools.htmlelements.utils;

import ch.lambdaj.function.convert.Converter;
import org.openqa.selenium.WebElement;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.03.13
 */
public class WebElementToTextConverter implements Converter<WebElement, String> {

    public static Converter<WebElement, String> toText() {
        return new WebElementToTextConverter();
    }

    @Override
    public String convert(WebElement element) {
        return element.getText();
    }
}

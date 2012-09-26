package ru.yandex.qatools.htmlelements.loader.decorator;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import ru.yandex.qatools.htmlelements.pagefactory.DefaultElementLocator;

import java.lang.reflect.Field;

/**
 * A factory for producing {@link DefaultElementLocator} instances.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 15.08.12
 */
public class HtmlElementLocatorFactory implements ElementLocatorFactory {
    private final SearchContext searchContext;

    public HtmlElementLocatorFactory(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        return new DefaultElementLocator(searchContext, new HtmlElementFieldAnnotationsHandler(field));
    }
}

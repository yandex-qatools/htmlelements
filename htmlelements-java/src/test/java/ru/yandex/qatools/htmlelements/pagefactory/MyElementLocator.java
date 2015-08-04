package ru.yandex.qatools.htmlelements.pagefactory;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;

public class MyElementLocator extends DefaultElementLocator {
    public MyElementLocator(SearchContext searchContext, AbstractAnnotations annotationsHandler) {
        super(searchContext, annotationsHandler);
    }

    @Override
    public WebElement findElement() {
        throw new MyException("I'll never find any elements for you!");
    }

    public class MyException extends RuntimeException {
        public MyException(String string) {
            super(string);
        }
    }
}

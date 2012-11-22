package ru.yandex.qatools.htmlelements.pagefactory;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class CustomElementLocator extends DefaultElementLocator {

	public CustomElementLocator(SearchContext searchContext,
			AnnotationsHandler annotationsHandler) {
		super(searchContext, annotationsHandler);
	}
	
	public WebElement findElement() {
		throw new RuntimeException("I'll never find you any elements!");
	}

}

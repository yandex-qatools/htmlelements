package ru.yandex.qatools.htmlelements.pagefactory;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class CustomElementLocator extends DefaultElementLocator {

	public CustomElementLocator(SearchContext searchContext,
			AnnotationsHandler annotationsHandler) {
		super(searchContext, annotationsHandler);
	}
	
	@Override
	public WebElement findElement() {
		throw new CustomException("I'll never find you any elements!");
	}
	
	public class CustomException extends RuntimeException {

		public CustomException(String string) {
			super(string);
		}
		
	}

}

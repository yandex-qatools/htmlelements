package ru.yandex.qatools.htmlelements;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocator;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocator.CustomException;
import ru.yandex.qatools.htmlelements.testelements.Company;
import ru.yandex.qatools.htmlelements.testpages.SearchPage;

public class CustomElementLocatorTest {
	private WebDriver webDriver;
	
	@Before
	public void createStubs() {
		webDriver = mock(WebDriver.class);
		
		WebElement wrapper = mock(WebElement.class);
		when(webDriver.findElement(By.cssSelector(ComplexStructTest.WRAPPER_CSS))).thenReturn(wrapper);
	}
	
	@Test(expected = CustomException.class)
	public void forHtmlElement() {
		Company company = HtmlElementLoader.create(Company.class, CustomElementLocator.class, webDriver);
		company.getText();
	}
	
	@Test(expected = CustomException.class)
	public void forPageObject() {
		SearchPage searchPage = new SearchPage(CustomElementLocator.class);
		searchPage.getLogo().getText();
	}
}

package ru.yandex.qatools.htmlelements.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ByAll;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Murzinov Ilya, murz42@gmail.com
 *         Date: 29.04.14
 */
public class FindAllPage {
    public FindAllPage() {
    }

    public FindAllPage(WebDriver webDriver) {
        /* required for PageInitializationTest */
    }

    private static final String ELEMENT_ID = "element";
    private static final String HTML_ELEMENT_ID = "html-element";
    private static final String TEXT_INPUT_ID = "text-input";
    private static final String BUTTON_ID = "button";
    private static final String RADIO_NAME = "radio";

    @FindAll({@FindBy(id = ELEMENT_ID)})
    private WebElement findAllElement;

    @FindAll({@FindBy(id = HTML_ELEMENT_ID)})
    private HtmlElement findAllHtmlElement;

    @FindAll({@FindBy(id = ELEMENT_ID)})
    private List<WebElement> findAllWebElementList;

    @FindAll({@FindBy(id = ELEMENT_ID),
            @FindBy(id = HTML_ELEMENT_ID),
            @FindBy(id = BUTTON_ID)})
    private HtmlElement findAllMultipleByHtmlElement;

    @FindAll({@FindBy(id = ELEMENT_ID), @FindBy(id = HTML_ELEMENT_ID)})
    private List<WebElement> findAllMultipleByWebElementList;

    public WebElement getFindAllElement() {
        return findAllElement;
    }

    public HtmlElement getFindAllHtmlElement() {
        return findAllHtmlElement;
    }

    public List<WebElement> getFindAllWebElementList() {
        return findAllWebElementList;
    }

    public HtmlElement getFindAllMultipleByHtmlElement() {
        return findAllMultipleByHtmlElement;
    }

    public List<WebElement> getFindAllMultipleByWebElementList() {
        return findAllMultipleByWebElementList;
    }

    public static WebDriver mockDriver() {
        WebDriver driver = mock(WebDriver.class);
        WebElement element = mock(WebElement.class);
        WebElement htmlElement = mock(WebElement.class);
        WebElement textInput = mock(WebElement.class);
        WebElement button = mock(WebElement.class);
        WebElement radioButton = mock(WebElement.class);
        List<WebElement> radioGroup = Arrays.asList(radioButton, radioButton, radioButton);
        List<WebElement> textInputList = Arrays.asList(textInput, textInput, textInput);
        List<WebElement> htmlElementList = Arrays.asList(htmlElement, htmlElement, htmlElement);
        List<WebElement> webElementList = Arrays.asList(element, element, element);

        when(driver.findElements(new ByAll(By.id(ELEMENT_ID)))).thenReturn(webElementList);
        when(driver.findElements(new ByAll(By.id(ELEMENT_ID), By.id(HTML_ELEMENT_ID))))
                .thenReturn(Arrays.asList(element, element, element, htmlElement, htmlElement, htmlElement));
        when(driver.findElement(new ByAll(By.id(ELEMENT_ID)))).thenReturn(element);
        when(driver.findElement(new ByAll(By.id(HTML_ELEMENT_ID)))).thenReturn(htmlElement);
        when(driver.findElement(new ByAll(By.id(ELEMENT_ID), By.id(HTML_ELEMENT_ID), By.id(BUTTON_ID))))
                .thenReturn(htmlElement);

        return driver;
    }
}

package ru.yandex.qatools.htmlelements.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.*;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class AllElementTypesPage {
    public AllElementTypesPage() {
    }

    public AllElementTypesPage(WebDriver webDriver) {
        /* required for PageInitializationTest */
    }

    private static final String ELEMENT_ID = "element";
    private static final String HTML_ELEMENT_ID = "html-element";
    private static final String TEXT_INPUT_ID = "text-input";
    private static final String BUTTON_ID = "button";
    private static final String RADIO_NAME = "radio";
    private static final String IMAGE_NAME = "image";

    @FindBy(id = ELEMENT_ID)
    private WebElement element;

    @FindBy(id = HTML_ELEMENT_ID)
    private HtmlElement htmlElement;

    @FindBy(id = TEXT_INPUT_ID)
    private TextInput textInput;

    @FindBy(id = BUTTON_ID)
    private Button button;

    @FindBy(name = RADIO_NAME)
    private Radio radio;

    @FindBy(name = IMAGE_NAME)
    private Image image;

    @FindBy(id = TEXT_INPUT_ID)
    private List<TextInput> textInputList;

    @FindBy(id = HTML_ELEMENT_ID)
    private List<HtmlElement> htmlElementList;

    @FindBy(id = ELEMENT_ID)
    private List<WebElement> webElementList;

    public WebElement getElement() {
        return element;
    }

    public HtmlElement getHtmlElement() {
        return htmlElement;
    }

    public TextInput getTextInput() {
        return textInput;
    }

    public Button getButton() {
        return button;
    }

    public Radio getRadio() {
        return radio;
    }

    public Image getImage() {
        return image;
    }

    public List<TextInput> getTextInputList() {
        return textInputList;
    }

    public List<HtmlElement> getHtmlElementList() {
        return htmlElementList;
    }

    public List<WebElement> getWebElementList() {
        return webElementList;
    }

    public static WebDriver mockDriver() {
        WebDriver driver = mock(WebDriver.class);
        WebElement element = mock(WebElement.class);
        WebElement htmlElement = mock(WebElement.class);
        WebElement textInput = mock(WebElement.class);
        WebElement button = mock(WebElement.class);
        WebElement radioButton = mock(WebElement.class);
        WebElement image = mock(WebElement.class);
        List<WebElement> radioGroup = asList(radioButton, radioButton, radioButton);
        List<WebElement> textInputList = asList(textInput, textInput, textInput);
        List<WebElement> htmlElementList = asList(htmlElement, htmlElement, htmlElement);
        List<WebElement> webElementList = asList(element, element, element);

        when(driver.findElement(By.id(ELEMENT_ID))).thenReturn(element);
        when(driver.findElement(By.id(HTML_ELEMENT_ID))).thenReturn(htmlElement);
        when(driver.findElement(By.id(TEXT_INPUT_ID))).thenReturn(textInput);
        when(driver.findElement(By.id(BUTTON_ID))).thenReturn(button);
        when(driver.findElement(By.name(RADIO_NAME))).thenReturn(radioButton);
        when(driver.findElement(By.name(IMAGE_NAME))).thenReturn(image);

        when(driver.findElements(By.name(RADIO_NAME))).thenReturn(radioGroup);
        when(driver.findElements(By.id(TEXT_INPUT_ID))).thenReturn(textInputList);
        when(driver.findElements(By.id(HTML_ELEMENT_ID))).thenReturn(htmlElementList);
        when(driver.findElements(By.id(ELEMENT_ID))).thenReturn(webElementList);

        when(radioButton.getAttribute("name")).thenReturn(RADIO_NAME);
        String xpath = String.format("self::* | following::input[@type = 'radio' and @name = '%s'] | " +
                "preceding::input[@type = 'radio' and @name = '%s']", RADIO_NAME, RADIO_NAME);
        when(radioButton.findElements(By.xpath(xpath))).thenReturn(radioGroup);

        return driver;
    }

}

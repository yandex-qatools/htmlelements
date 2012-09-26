package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Radio;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class PageInitializationTest {
    private WebDriver driver = Page.mockDriver();

    public static class Page {
        private static final String ELEMENT_ID = "element";
        private static final String HTML_ELEMENT_ID = "html-element";
        private static final String TEXT_INPUT_ID = "text-input";
        private static final String BUTTON_ID = "button";
        private static final String RADIO_NAME = "radio";

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
            List<WebElement> radioGroup = Arrays.asList(radioButton, radioButton, radioButton);
            List<WebElement> textInputList = Arrays.asList(textInput, textInput, textInput);
            List<WebElement> htmlElementList = Arrays.asList(htmlElement, htmlElement, htmlElement);
            List<WebElement> webElementList = Arrays.asList(element, element, element);

            when(driver.findElement(By.id(ELEMENT_ID))).thenReturn(element);
            when(driver.findElement(By.id(HTML_ELEMENT_ID))).thenReturn(htmlElement);
            when(driver.findElement(By.id(TEXT_INPUT_ID))).thenReturn(textInput);
            when(driver.findElement(By.id(BUTTON_ID))).thenReturn(button);
            when(driver.findElement(By.name(RADIO_NAME))).thenReturn(radioButton);

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

    @Test
    public void testInitializationOfCreatedPage() {
        Page page = new Page();

        assertThat("'element' field of page object should be null before initialization",
                page.getElement(), is(nullValue()));
        assertThat("'htmlElement' field of page object should be null before initialization",
                page.getHtmlElement(), is(nullValue()));
        assertThat("'textInput' field of page object should be null before initialization",
                page.getTextInput(), is(nullValue()));
        assertThat("'button' field of page object should be null before initialization",
                page.getButton(), is(nullValue()));
        assertThat("'radio' field of page object should be null before initialization",
                page.getRadio(), is(nullValue()));
        assertThat("'textInputList' field of page object should be null before initialization",
                page.getTextInputList(), is(nullValue()));
        assertThat("'htmlElementList' field of page object should be null before initialization",
                page.getHtmlElementList(), is(nullValue()));
        assertThat("'webElementList' field of page object should be null before initialization",
                page.getWebElementList(), is(nullValue()));

        HtmlElementLoader.populate(page, driver);

        assertThat("'element' field of page object should be not null after initialization",
                page.getElement(), is(notNullValue()));
        assertThat("'htmlElement' field of page object should be not null after initialization",
                page.getHtmlElement(), is(notNullValue()));
        assertThat("'textInput' field of page object should be not null after initialization",
                page.getTextInput(), is(notNullValue()));
        assertThat("'button' field of page object should be not null after initialization",
                page.getButton(), is(notNullValue()));
        assertThat("'radio' field of page object should be not null after initialization",
                page.getRadio(), is(notNullValue()));
        assertThat("'radio' field of page object should have 3 radio buttons",
                page.getRadio().getButtons().size(), is(equalTo(3)));
        assertThat("'textInputList' field of page object should be not null after initialization",
                page.getTextInputList(), is(notNullValue()));
        assertThat("'textInputList' field of page object should have 3 text inputs",
                page.getTextInputList().size(), is(equalTo(3)));
        assertThat("'htmlElementList' field of page object should be not null after initialization",
                page.getHtmlElementList(), is(notNullValue()));
        assertThat("'htmlElementList' field of page object should have 3 text inputs",
                page.getHtmlElementList().size(), is(equalTo(3)));
        assertThat("'webElementList' field of page object should be not null after initialization",
                page.getWebElementList(), is(notNullValue()));
        assertThat("'webElementList' field of page object should have 3 text inputs",
                page.getWebElementList().size(), is(equalTo(3)));
    }

    @Test
    public void testPageCreationAndInitialization() {
        Page page = HtmlElementLoader.create(Page.class, driver);

        assertThat("'element' field of page object should be not null after instantiation and initialization",
                page.getElement(), is(notNullValue()));
        assertThat("'htmlElement' field of page object should be not null after initialization",
                page.getHtmlElement(), is(notNullValue()));
        assertThat("'textInput' field of page object should be not null after instantiation and initialization",
                page.getTextInput(), is(notNullValue()));
        assertThat("'button' field of page object should be not null after instantiation and initialization",
                page.getButton(), is(notNullValue()));
        assertThat("'radio' field of page object should be not null after initialization",
                page.getRadio(), is(notNullValue()));
        assertThat("'radio' field of page object should have 3 radio buttons",
                page.getRadio().getButtons().size(), is(equalTo(3)));
        assertThat("'textInputList' field of page object should be not null after initialization",
                page.getTextInputList(), is(notNullValue()));
        assertThat("'textInputList' field of page object should have 3 text inputs",
                page.getTextInputList().size(), is(equalTo(3)));
        assertThat("'htmlElementList' field of page object should be not null after initialization",
                page.getHtmlElementList(), is(notNullValue()));
        assertThat("'htmlElementList' field of page object should have 3 text inputs",
                page.getHtmlElementList().size(), is(equalTo(3)));
        assertThat("'webElementList' field of page object should be not null after initialization",
                page.getWebElementList(), is(notNullValue()));
        assertThat("'webElementList' field of page object should have 3 text inputs",
                page.getWebElementList().size(), is(equalTo(3)));
    }
}

package ru.yandex.qatools.htmlelements;

import org.apache.commons.lang3.reflect.FieldUtils;

import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Table;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.lang.reflect.Field;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Anikeev iamanikeev@gmail.com
 */
public class UsingTableTest {

    private WebDriver driver = mock(WebDriver.class);
    private WebElement formWithTable = mock(WebElement.class);
    private WebElement tableOfContents = mock(WebElement.class);

    @Before
    public void init() throws IllegalAccessException {
        when(driver.findElement(By.className("formWithTable"))).thenReturn(formWithTable);
        when(formWithTable.findElement(By.name("tableOfContents"))).thenReturn(tableOfContents);
        when(tableOfContents.findElements(By.xpath(".//th"))).thenReturn(TableElement.headers);
        when(tableOfContents.findElements(By.xpath(".//tr"))).thenReturn(TableElement.trs);
        when(TableElement.tr1.findElements(By.xpath(".//td"))).thenReturn(TableElement.tr1tds);
        when(TableElement.tr2.findElements(By.xpath(".//td"))).thenReturn(TableElement.tr2tds);
        // All WebElements return field name as getText()
        for (Field field : FieldUtils.getAllFields(TableElement.class)) {
            if (field.getType().equals(WebElement.class)) {
                when(((WebElement) FieldUtils.readStaticField(TableElement.class, field.getName(), true)).getText())
                        .thenReturn(field.getName());
            }
        }
    }

    @Test
    public void getHeadings() {
        FormWithTable form = new FormWithTable(driver);
        assertThat(form.tableOfContents.getHeadings().size(), equalTo(3));
        assertThat(form.tableOfContents.getHeadings().get(0).getText(), equalTo("header1"));
    }

    @Test
    public void getRows() {
        FormWithTable form = new FormWithTable(driver);
        assertThat(form.tableOfContents.getRows().size(), equalTo(2));
        assertThat(form.tableOfContents.getRows().get(0).get(0).getText(), equalTo("r1td1"));
    }

    @Test
    public void getHeadingsAsString() {
        FormWithTable form = new FormWithTable(driver);
        assertThat(form.tableOfContents.getHeadingsAsString().size(), equalTo(3));
        assertThat(form.tableOfContents.getHeadingsAsString().get(0), equalTo("header1"));
    }

    @Test
    public void getRowsAsString() {
        FormWithTable form = new FormWithTable(driver);
        assertThat(form.tableOfContents.getRowsAsString().size(), equalTo(2));
        assertThat(form.tableOfContents.getRowsAsString().get(0).get(0), equalTo("r1td1"));
    }

    @Test
    public void getRowsAsStringMappedToHeadings() {
        FormWithTable form = new FormWithTable(driver);
        assertThat(form.tableOfContents.getRowsAsStringMappedToHeadings().size(), equalTo(2));
        assertThat(form.tableOfContents.getRowsAsStringMappedToHeadings().get(1).containsKey("header1"), equalTo(true));
        assertThat(form.tableOfContents.getRowsAsStringMappedToHeadings().get(1).get("header2"), equalTo("r2td2"));
    }

    @Test
    public void getRowsAsStringMappedToParticularHeadings() {
        List<String> headings = asList("header2", "header3");
        FormWithTable form = new FormWithTable(driver);
        assertThat(form.tableOfContents.getRowsAsStringMappedToHeadings(headings).size(), equalTo(2));
        assertThat(form.tableOfContents.getRowsAsStringMappedToHeadings(headings).get(1).size(), equalTo(2));
        assertThat(form.tableOfContents.getRowsAsStringMappedToHeadings(headings)
                .get(1).containsKey("header1"), equalTo(false));
        assertThat(form.tableOfContents.getRowsAsStringMappedToHeadings(headings)
                .get(1).get("header2"), equalTo("r2td2"));
    }

    @FindBy(className = "formWithTable")
    public class FormWithTable extends HtmlElement {
        public FormWithTable(WebDriver driver) {
            HtmlElementLoader.populate(this, driver);
        }

        @FindBy(name = "tableOfContents")
        public Table tableOfContents;
    }

    private static class TableElement {
        /** Headers */
        static WebElement header1 = mock(WebElement.class);
        static WebElement header2 = mock(WebElement.class);
        static WebElement header3 = mock(WebElement.class);
        /** Rows */
        static WebElement tr1 = mock(WebElement.class);
        static WebElement tr2 = mock(WebElement.class);
        /** First row elements */
        static WebElement r1td1 = mock(WebElement.class);
        static WebElement r1td2 = mock(WebElement.class);
        static WebElement r1td3 = mock(WebElement.class);
        /** Second row elements */
        static WebElement r2td1 = mock(WebElement.class);
        static WebElement r2td2 = mock(WebElement.class);
        static WebElement r2td3 = mock(WebElement.class);

        static List<WebElement> trs = asList(tr1, tr2);
        static List<WebElement> tr1tds = asList(r1td1, r1td2, r1td3);
        static List<WebElement> tr2tds = asList(r2td1, r2td2, r2td3);
        static List<WebElement> headers = asList(header1, header2, header3);
    }
}

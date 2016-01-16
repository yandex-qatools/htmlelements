package ru.yandex.qatools.htmlelements;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
public class UsingCheckboxTest {
    WebDriver driver = mock(WebDriver.class);
    WebElement loginForm = mock(WebElement.class);
    WebElement checkBoxInput = mock(WebElement.class);
    WebElement checkBoxLabel = mock(WebElement.class);

    @Before
    public void init() {
        when(driver.findElement(By.className("loginform"))).thenReturn(loginForm);
        when(loginForm.findElement(By.name("dontremember"))).thenReturn(checkBoxInput);
        when(checkBoxInput.findElement(By.xpath("following-sibling::label"))).thenReturn(checkBoxLabel);
    }

    @Test
    /** CheckBox element returns its label text */
    public void getText() {
        when(checkBoxLabel.getText()).thenReturn("foreign PC");

        LoginForm form = new LoginForm(driver);
        assertThat(form.foreignPC.getLabelText(), equalTo("foreign PC"));
    }

    @Test
    /** Click on unchecked element to select it */
    public void select() {
        when(checkBoxInput.isSelected()).thenReturn(false);

        LoginForm form = new LoginForm(driver);
        form.foreignPC.select();

        verify(checkBoxInput).click();
    }

    @Test
    /** Click on checked element to deselect it */
    public void deselect() {
        when(checkBoxInput.isSelected()).thenReturn(true);

        LoginForm form = new LoginForm(driver);
        form.foreignPC.deselect();

        verify(checkBoxInput).click();
    }

    @FindBy(className = "loginform")
    public class LoginForm extends HtmlElement {
        public LoginForm(WebDriver driver) {
            HtmlElementLoader.populate(this, driver);
        }

        @FindBy(name = "dontremember")
        public CheckBox foreignPC;
    }
}
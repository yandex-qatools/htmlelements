package ru.yandex.qatools.htmlelements;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
public class UsingSelectTest {
    @Test
    public void withSimpleElement() {
        WebDriver driver = mock(WebDriver.class);
        WebElement registerForm = mock(WebElement.class);
        WebElement countryBoxElement = mock(WebElement.class);

        when(driver.findElement(By.className("regform"))).thenReturn(registerForm);
        when(registerForm.findElement(By.name("country"))).thenReturn(countryBoxElement);
        when(countryBoxElement.getTagName()).thenReturn("select");
        when(countryBoxElement.getDomAttribute("multiple")).thenReturn("true");

        RegisterForm form = new RegisterForm(driver);
        form.country.deselectAll();
    }

    @FindBy(className = "regform")
    public class RegisterForm extends HtmlElement {
        public RegisterForm(WebDriver driver) {
            HtmlElementLoader.populate(this, driver);
        }

        @FindBy(name = "country")
        public Select country;
    }
}
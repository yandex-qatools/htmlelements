package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.openqa.selenium.By;
import ru.yandex.qatools.htmlelements.testpages.NotAnnotatedFieldsPage;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 1.07.12
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class NotAnnotatedFieldsPageTest {
    private NotAnnotatedFieldsPage page = new NotAnnotatedFieldsPage();

    @Test(expected = NullPointerException.class)
    public void notAnnotatedWebElementShouldThrowExceptionOnAnyMethodCall() {
        page.getWebElement().getTagName();
    }

    @Test(expected = NullPointerException.class)
    public void notAnnotatedHtmlElementShouldThrowExceptionOnAnyMethodCall() {
        page.getHtmlElement().getTagName();
    }

    @Test(expected = NullPointerException.class)
    public void notAnnotatedCheckBoxShouldThrowExceptionOnAnyMethodCall() {
        page.getCheckBox().isSelected();
    }

    @Test(expected = NullPointerException.class)
    public void notAnnotatedSelectShouldThrowExceptionOnAnyMethodCall() {
        page.getSelect().isMultiple();
    }
}

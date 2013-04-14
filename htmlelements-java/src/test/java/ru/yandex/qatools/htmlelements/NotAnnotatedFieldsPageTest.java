package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.testpages.NotAnnotatedFieldsPage;

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

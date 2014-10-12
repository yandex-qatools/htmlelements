package ru.yandex.qatools.htmlelements.testelements;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.ComplexStructTest;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 *         Date: 12.10.14
 */
@FindBy(css = ComplexStructTest.COMPANY_CSS)
public class Company extends HtmlElement {

    @Name(ComplexStructTest.VACANCY_LIST_NAME)
    @FindBy(css = ComplexStructTest.VACANCY_CSS)
    public List<HtmlElement> vacancyList;

}
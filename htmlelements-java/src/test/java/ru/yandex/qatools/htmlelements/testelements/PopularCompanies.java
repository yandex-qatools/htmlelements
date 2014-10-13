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
@Name(ComplexStructTest.WRAPPER_NAME)
@FindBy(css = ComplexStructTest.WRAPPER_CSS)
public class PopularCompanies extends HtmlElement {

    @Name(ComplexStructTest.COMPANIES_LIST_NAME)
    public List<Company> companies;

}
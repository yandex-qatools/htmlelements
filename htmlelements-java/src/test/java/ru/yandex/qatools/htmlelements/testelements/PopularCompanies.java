package ru.yandex.qatools.htmlelements.testelements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.ComplexStructTest;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
@Name(ComplexStructTest.WRAPPER_NAME)
@FindBy(css = ComplexStructTest.WRAPPER_CSS)
public class PopularCompanies extends HtmlElement {

    @Name(ComplexStructTest.COMPANIES_LIST_NAME)
    public List<Company> companies;

}
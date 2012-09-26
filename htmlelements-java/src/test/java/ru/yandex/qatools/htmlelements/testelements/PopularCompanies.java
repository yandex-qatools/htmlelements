package ru.yandex.qatools.htmlelements.testelements;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.ComplexStructTest;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name(ComplexStructTest.WRAPPER_NAME)
@Block(@FindBy(css = ComplexStructTest.WRAPPER_CSS))
public class PopularCompanies extends HtmlElement {

    @Name(ComplexStructTest.COMPANIES_LIST_NAME)
    public List<Company> companies;

}
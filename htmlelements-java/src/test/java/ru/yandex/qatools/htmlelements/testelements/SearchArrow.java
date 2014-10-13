package ru.yandex.qatools.htmlelements.testelements;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 30.06.12
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 *         Date: 12.10.14
 */
@Name(SearchArrowData.SEARCH_ARROW_NAME)
@FindBy(className = SearchArrowData.SEARCH_ARROW_CLASS)
public class SearchArrow extends HtmlElement {
    @Name(SearchArrowData.REQUEST_INPUT_NAME)
    @FindBy(className = SearchArrowData.REQUEST_INPUT_CLASS)
    private TextInput requestInput;

    @FindBy(className = SearchArrowData.SEARCH_BUTTON_CLASS)
    private Button searchButton;

    @FindBy(className = SearchArrowData.SUGGEST_CLASS)
    private List<WebElement> suggestList;

    public TextInput getRequestInput() {
        return requestInput;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public List<WebElement> getSuggestList() {
        return suggestList;
    }
}

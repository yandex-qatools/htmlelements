package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.openqa.selenium.Keys;
import ru.yandex.qatools.htmlelements.testelements.MockedForm;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.03.13
 */
public class FormFillingTest {
    private static final String INPUT_TEXT_TO_SEND = "text";
    private static final String INPUT_WITH_TEXT_KEYS_TO_SEND = Keys.DELETE.toString() + Keys.BACK_SPACE.toString() + "text";
    private static final boolean CHECKBOX_VALUE_TO_SET = true;

    private final MockedForm form = new MockedForm();

    public void fillForm() {
        // Prepare data to fill form with
        Map<String, Object> data = new HashMap<>();
        data.put(MockedForm.TEXT_INPUT_NAME, INPUT_TEXT_TO_SEND);
        data.put(MockedForm.TEXT_INPUT_WITH_TEXT_NAME, INPUT_TEXT_TO_SEND);
        data.put(MockedForm.CHECKBOX_NAME, CHECKBOX_VALUE_TO_SET);
        data.put(MockedForm.RADIO_NAME, MockedForm.RADIO_BUTTON_VALUE);
        data.put(MockedForm.SELECT_NAME, MockedForm.SELECT_OPTION_VALUE);
        data.put(MockedForm.TEXT_AREA_NAME, INPUT_TEXT_TO_SEND);
        data.put(MockedForm.TEXT_AREA_WITH_TEXT_NAME, INPUT_TEXT_TO_SEND);

        // Fill form
        form.fill(data);
    }

    @Test
    public void formFieldsShouldBeFilledCorrectly() {
        fillForm();
        verify(form.getTextInput()).sendKeys(INPUT_TEXT_TO_SEND);
        verify(form.getTextInputWithText()).sendKeys(INPUT_WITH_TEXT_KEYS_TO_SEND);
        verify(form.getCheckBox()).click();
        verify(form.getRadioButton()).click();
        verify(form.getSelectOption()).click();
        verify(form.getTextArea()).sendKeys(INPUT_TEXT_TO_SEND);
        verify(form.getTextAreaWithText()).sendKeys(INPUT_WITH_TEXT_KEYS_TO_SEND);
    }

    @Test
    public void clearingFieldWhenNullIsPassed() {
        Map<String, Object> data = new HashMap<>();
        data.put(MockedForm.TEXT_INPUT_WITH_TEXT_NAME, null);
        form.fill(data);
        verify(form.getTextInputWithText()).sendKeys(Keys.DELETE.toString() + Keys.BACK_SPACE.toString());
    }
}

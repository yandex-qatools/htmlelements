package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.testpages.NonElementFieldsPage;

/**
 * @author Graham Russell graham@ham1.co.uk
 *         Date: 24.10.2015
 */
public class NonElementFieldsTest {

    @Test
    public void non_element_private_fields_should_not_be_decorated_as_elements() {
        new NonElementFieldsPage();
    }
}

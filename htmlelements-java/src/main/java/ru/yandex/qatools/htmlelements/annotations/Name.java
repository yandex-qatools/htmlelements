package ru.yandex.qatools.htmlelements.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that is used to set a name for a block of elements or for an element in a block
 * or for a page object element.
 * <p/>
 * For example:
 * <p/>
 * <pre class="code">
 * &#64;Name("My Form")
 * &#64;Block(&#64;FindBy(css = "my_form_css"))
 * public class MyForm extends HtmlElement {
 * &#64;Name("Text input name")
 * &#64;FindBy(css = "text_input_css")
 * private TextInput textInput;
 * <p/>
 * // Other elements and methods here
 * }
 * </pre>
 *
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 17.06.12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Name {
    String value();
}

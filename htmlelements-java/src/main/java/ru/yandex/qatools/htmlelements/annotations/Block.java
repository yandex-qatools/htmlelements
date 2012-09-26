package ru.yandex.qatools.htmlelements.annotations;

import org.openqa.selenium.support.FindBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that is used to mark a block of elements to specify the way of locating it.
 * <p/>
 * Use this annotation to mark a descendant of {@link ru.yandex.qatools.htmlelements.element.HtmlElement} class
 * that represents a block of elements (e.g. web form) and specify its locator with {@link FindBy} annotation inside.
 * <p/>
 * For example:
 * <p/>
 * <pre class="code">
 * &#64;Block(&#64;FindBy(css = "my_form_css"))
 * public class MyForm extends HtmlElement {
 *     // Your form elements here
 * }
 * </pre>
 *
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 17.06.12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Block {
    public FindBy value();
}

package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents default web page form tag.
 *
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 * 3/14/13, 4:38 PM
 * @author Alexander Tolmachev starlight@yandex-team.ru
 * Date: 26.03.13
 * @author Dzmitry Sankouski sankouski.dzmitry@gmail.com
 * Date: 05.03.2020
 */
public class Form extends FormBase {
    /**
     * Fillable operations definition
     * */
    private static List<FillableOps> fillableOpsList = Arrays.asList(
            new FillableOps(
                    CheckBox::new,
                    element -> {
                        String tagName = element.getTagName();
                        String type = element.getAttribute("type");
                        return "input".equals(tagName) && "checkbox".equals(type);
                    }),
            new FillableOps(
                    Radio::new,
                    element -> {
                        String tagName = element.getTagName();
                        String type = element.getAttribute("type");
                        return "input".equals(tagName) && "radio".equals(type);
                    }),
            new FillableOps(
                    FileInput::new,
                    element -> {
                        String tagName = element.getTagName();
                        String type = element.getAttribute("type");
                        return "input".equals(tagName) && "file".equals(type);
                    }),
            new FillableOps(
                    Select::new,
                    element -> {
                        String tagName = element.getTagName();
                        return "select".equals(tagName);
                    }),
            new FillableOps(
                    TextInput::new,
                    element -> {
                        String tagName = element.getTagName();
                        return "input".equals(tagName) || "textarea".equals(tagName);
                    })
    );

    public Form(WebElement wrappedElement) {
        super(wrappedElement, fillableOpsList);
    }

    public Form(WebElement wrappedElement, List<FillableOps> additionalElements) {
        super(wrappedElement, Stream.concat(
                additionalElements.stream(), fillableOpsList.stream()).collect(Collectors.toList()));
    }
}

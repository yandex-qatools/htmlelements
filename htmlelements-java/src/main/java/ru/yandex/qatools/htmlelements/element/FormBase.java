package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

/**
 * Represents web page form tag.
 * Provides handy way of filling form with data and submitting it.
 * <p>
 * Form use {@link Fillable} elements, to perform filling.
 * You should extend this class and define operations for {@link Fillable} you want to use.
 *
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 * 3/14/13, 4:38 PM
 * @author Alexander Tolmachev starlight@yandex-team.ru
 * Date: 26.03.13
 * @author Dzmitry Sankouski sankouski.dzmitry@gmail.com
 * Date: 05.03.2020
 */
public abstract class FormBase extends TypifiedElement {
    /**
     * Operations for supported fillable types
     */
    private List<FillableOps> fillableOpsList;

    /**
     * Specifies {@link org.openqa.selenium.WebElement} representing form tag.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     * @param fillableOpsList Fillable operations to register. See {@link FillableOps#FillableOps(Function, Predicate)}
     *                        Order is important, because {@link #fill(Map)} finds first fillable it can fill.
     */
    public FormBase(WebElement wrappedElement, List<FillableOps> fillableOpsList) {
        super(wrappedElement);
        this.fillableOpsList = fillableOpsList;
    }

    /**
     * Fills form with data contained in passed map.
     * For each map entry if an input with a name coincident with entry key exists
     * it is filled with string representation of entry value.
     * If an input with such a name is not found the corresponding entry is skipped.
     *
     * @param data Map containing data to fill form inputs with.
     */
    public void fill(Map<String, Object> data) {
        data.entrySet().stream()
                .map(e -> new AbstractMap.SimpleEntry<>(
                        findElementByKey(e.getKey()),
                        Objects.toString(e.getValue(), "")))
                .filter(e -> !isNull(e.getKey()))
                /* Convert WebElement to supported Fillable */
                .map(elementEntry -> new AbstractMap.SimpleEntry<>(
                        fillableOpsList.stream()
                                .filter(supportedFillable -> supportedFillable.getCanFill().test(elementEntry.getKey()))
                                .map(supportedFillable -> supportedFillable.getWrapIntoFillable().apply(elementEntry.getKey()))
                                .findFirst()
                                .orElse(null),
                        elementEntry.getValue()
                ))
                .filter(e -> !isNull(e.getKey()))
                .forEach(e -> e.getKey().fill(e.getValue()));
    }

    protected WebElement findElementByKey(String key) {
        List<WebElement> elements = getWrappedElement().findElements(By.name(key));
        if (elements.isEmpty()) {
            return null;
        } else {
            return elements.get(0);
        }
    }

    protected static class FillableOps {
        private Function<WebElement, Fillable> wrapIntoFillable;
        private Predicate<WebElement> canFill;

        /**
         * To add custom {@link Fillable} element support, add entry with
         * @param wrapIntoFillable Function to create Fillable from instance of {@link WebElement}
         * @param canFill Predicate to test, if instance of {@link WebElement} can be represented by this Fillable
         * */
        public FillableOps(Function<WebElement, Fillable> wrapIntoFillable, Predicate<WebElement> canFill) {
            this.wrapIntoFillable = wrapIntoFillable;
            this.canFill = canFill;
        }

        public Function<WebElement, Fillable> getWrapIntoFillable() {
            return wrapIntoFillable;
        }

        public Predicate<WebElement> getCanFill() {
            return canFill;
        }
    }
}

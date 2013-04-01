package ru.yandex.qatools.htmlelements.utils;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.Converter;

import java.util.List;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 01.04.13
 */
public class ListConverter<F, T> implements Converter<List<F>, List<T>> {
    private final Converter<F, T> itemsConverter;

    public ListConverter(Converter<F, T> itemsConverter) {
        this.itemsConverter = itemsConverter;
    }

    @Override
    public List<T> convert(List<F> list) {
        return Lambda.convert(list, itemsConverter);
    }
}

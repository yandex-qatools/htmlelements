package ru.yandex.qatools.htmlelements.utils;

import ch.lambdaj.function.convert.Converter;

import java.util.Map;

import static ch.lambdaj.Lambda.convertMap;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.03.13
 */
public class MapConverter<K, F, T> implements Converter<Map<K, F>, Map<K, T>> {
    private final Converter<F, T> valueConverter;

    public MapConverter(Converter<F, T> valueConverter) {
        this.valueConverter = valueConverter;
    }

    @Override
    public Map<K, T> convert(Map<K, F> map) {
        return convertMap(map, valueConverter);
    }
}

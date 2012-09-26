package ru.yandex.qatools.htmlelements.element;

/**
 * Represents a named entity. Used to operate with names of blocks and typified elements.
 *
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 30.06.12
 */
public interface Named {
    /**
     * Returns name of the entity.
     *
     * @return {@code String} representing name.
     */
    String getName();
}

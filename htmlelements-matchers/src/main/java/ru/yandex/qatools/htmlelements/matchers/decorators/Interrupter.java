package ru.yandex.qatools.htmlelements.matchers.decorators;

/**
 * User: eoff
 * Date: 14.02.13
 * @author Maksim Mukosey eoff@yandex-team.ru
 *
 * Interrupter checks if the program is interrupted before.
 *
 */
public interface Interrupter {
    boolean isInterrupted();
}

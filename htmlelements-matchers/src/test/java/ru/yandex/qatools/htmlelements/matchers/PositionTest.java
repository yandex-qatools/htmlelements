package ru.yandex.qatools.htmlelements.matchers;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.matchers.testpages.PageForPositions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

public class PositionTest {

    PageForPositions page = new PageForPositions();
    @Test
    public void isHigherTest() {
        assertThat(page.high, PositionMatchers.higherThan(page.center));
    }

    @Test
    public void isLowerTest() {
        assertThat(page.low, PositionMatchers.lowerThan(page.center));
    }

    @Test
    public void isInterceptsTest() {
        assertThat(page.interceptor, PositionMatchers.intercepts(page.center));
        assertThat(page.interceptor, not(PositionMatchers.intercepts(page.inner)));
    }

    @Test
    public void isInsideTest() {
        assertThat(page.inner, PositionMatchers.inside(page.center));
        assertThat(page.interceptor, not(PositionMatchers.inside(page.center)));
    }
}

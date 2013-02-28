package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.openqa.selenium.WebDriver.Navigation;
import static ru.yandex.qatools.htmlelements.matchers.MatcherDecoratorFactory.should;
import static ru.yandex.qatools.htmlelements.matchers.decorators.RefreshPageAction.pageRefresh;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 12.02.13
 * Time: 0:08
 */
@RunWith(MockitoJUnitRunner.class)
public class RefreshPageMatcherDecoratorTest {
    public static final boolean THE_OBJECT = true;
    public static final boolean NOT_SAME_OBJECT = false;

    @Mock
    private WebDriver driver;
    @Mock
    private Navigation navigation;
    @Mock
    private Matcher<Object> matcher;

    @Test(expected = AssertionError.class)
    public void decoratedMatcherShouldNotMatchDifferentObjects() {
        when(driver.navigate()).thenReturn(navigation);

        assertThat(THE_OBJECT, should(is(NOT_SAME_OBJECT)).after(pageRefresh(driver)));
    }

    @Test
    public void decoratedWithRefreshPageActionMatcherShouldRefreshPage() {
        when(driver.navigate()).thenReturn(navigation);

        assertThat(THE_OBJECT, should(is(THE_OBJECT)).after(pageRefresh(driver)));

        verify(navigation, times(1)).refresh();
    }

    @Test
    public void decoratedWithRefreshPageActionMatcherShouldRefreshPageBeforeMatching() {
        when(driver.navigate()).thenReturn(navigation);
        when(matcher.matches(THE_OBJECT)).thenReturn(THE_OBJECT);

        assertThat(THE_OBJECT, should(matcher).after(pageRefresh(driver)));

        InOrder inOrder = inOrder(matcher, navigation);
        inOrder.verify(navigation).refresh();
        inOrder.verify(matcher).matches(THE_OBJECT);
    }
}

package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.openqa.selenium.WebDriver.Navigation;
import static ru.yandex.qatools.htmlelements.matchers.RefreshMatcherDecorator.withPrerefresh;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 12.02.13
 * Time: 0:08
 */
@RunWith(MockitoJUnitRunner.class)
public class RefreshMatcherTest {

    public static final boolean THE_OBJECT = true;
    public static final boolean NOT_SAME_OBJECT = false;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Mock
    private WebDriver driver;
    @Mock
    private Navigation navigation;
    @Mock
    private Matcher<Object> matcher;

    @Test
    public void isPrerefreshingMatching() {
        thrown.expect(AssertionError.class);

        when(driver.navigate()).thenReturn(navigation);
        assertThat(THE_OBJECT, withPrerefresh(is(NOT_SAME_OBJECT), driver));
    }


    @Test
    public void isPrerefreshingRefreshesDriver() {
        when(driver.navigate()).thenReturn(navigation);
        assertThat(THE_OBJECT, withPrerefresh(is(THE_OBJECT), driver));

        verify(navigation, times(1)).refresh();
    }


    @Test
    public void isPrerefreshRefreshBeforeMatching() {
        when(driver.navigate()).thenReturn(navigation);
        when(matcher.matches(THE_OBJECT)).thenReturn(THE_OBJECT);


        assertThat(THE_OBJECT, withPrerefresh(matcher, driver));

        InOrder inOrder = inOrder(matcher, navigation);

        inOrder.verify(navigation).refresh();
        inOrder.verify(matcher).matches(THE_OBJECT);
    }

}

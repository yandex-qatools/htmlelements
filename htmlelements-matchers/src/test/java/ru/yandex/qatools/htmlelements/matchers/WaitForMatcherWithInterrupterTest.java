package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.junit.Test;
import ru.yandex.qatools.htmlelements.matchers.decorators.Interrupter;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static ru.yandex.qatools.htmlelements.matchers.decorators.WaitForMatcherDecorator.withWaitFor;

/**
 * User: eoff
 * Date: 14.02.13
 */
public class WaitForMatcherWithInterrupterTest {

    public static final Object ANY_OBJECT = "";

    @Test(expected = AssertionError.class)
    public void decoratorShouldThrowAssertionExceptionWhenNotInterrupted() {
        Interrupter interrupter = mock(Interrupter.class);
        when(interrupter.isInterrupted()).thenReturn(false);

        assertThat(true, withWaitFor(equalTo(false), interrupter,  SECONDS.toMillis(2)));
    }

    @Test
    public void decoratorShouldTryWhileGetTrueWhenNotInterrupted() throws Exception {
        Matcher<Object> matcher = mock(Matcher.class);
        Interrupter interrupter = mock(Interrupter.class);
        when(interrupter.isInterrupted()).thenReturn(false);
        when(matcher.matches(any())).thenReturn(false, false, true);

        assertThat(new Object(), withWaitFor(matcher, interrupter));
        verify(interrupter, times(1)).isInterrupted();
        verify(matcher, times(3)).matches(any());
    }

    @Test
    public void decoratorShouldTryWhileTimerGoesOutWhenNotInterrupted() throws Exception {
        Matcher<Object> matcher = mock(Matcher.class);
        Interrupter interrupter = mock(Interrupter.class);
        when(interrupter.isInterrupted()).thenReturn(false);
        when(matcher.matches(any())).thenReturn(false);

        Boolean result = withWaitFor(matcher, interrupter, SECONDS.toMillis(2), MILLISECONDS.toMillis(500)).matches(ANY_OBJECT);
        // 4 for check + return = 5
        verify(interrupter, times(1)).isInterrupted();
        verify(matcher, times(5)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }

    @Test
    public void decoratorShouldNotWaitIfInterrupted() throws Exception {
        Matcher<Object> matcher = mock(Matcher.class);
        Interrupter interrupter = mock(Interrupter.class);
        when(interrupter.isInterrupted()).thenReturn(true);
        when(matcher.matches(any())).thenReturn(false, false, true);

        Boolean result = withWaitFor(matcher, interrupter, SECONDS.toMillis(2), MILLISECONDS.toMillis(500)).matches(ANY_OBJECT);
        verify(interrupter, times(1)).isInterrupted();
        verify(matcher, times(1)).matches(any());
        assertThat("Miracle! False now is true!", result, is(false));
    }

}

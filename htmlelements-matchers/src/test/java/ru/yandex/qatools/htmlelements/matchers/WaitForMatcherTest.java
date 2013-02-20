package ru.yandex.qatools.htmlelements.matchers;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.yandex.qatools.htmlelements.matchers.decorators.WaitForMatcherDecorator.withWaitFor;

import org.hamcrest.Matcher;
import org.junit.Test;

import ru.yandex.qatools.htmlelements.matchers.decorators.TimeoutCondition;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 10.01.13
 * Time: 19:39
 */
public class WaitForMatcherTest {

    public static final Object ANY_OBJECT = "";
    
    private TimeoutCondition twoSecondsExpired = new TimeoutCondition(SECONDS.toMillis(2));
    private Matcher<Object> matcher = mock(Matcher.class);

    @Test(expected = AssertionError.class)
    public void decoratorShouldThrowAssertionException() {
        assertThat(true, withWaitFor(equalTo(false)).withTimeout(SECONDS.toMillis(2)));
    }

    @Test
    public void decoratorShouldTryWhileGetTrue() throws Exception {
        when(matcher.matches(any())).thenReturn(false, false, true);

        assertThat(new Object(), withWaitFor(matcher));
        verify(matcher, times(3)).matches(any());
    }

    @Test
    public void decShouldTryWhileTimerGoesOut() throws Exception {
        when(matcher.matches(any())).thenReturn(false);

        Boolean result = withWaitFor(matcher).
        					withTimeout(SECONDS.toMillis(2)).
        					withInterval(MILLISECONDS.toMillis(250)).matches(ANY_OBJECT);
        // 8 for check + return = 9
        verify(matcher, times(9)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void untilUsage() {
        when(matcher.matches(any())).thenReturn(false);
        
        Boolean result = withWaitFor(matcher).orUntil(twoSecondsExpired).matches(ANY_OBJECT);
        
        verify(matcher, times(5)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void withTimeOut() {
        when(matcher.matches(any())).thenReturn(false);
        
        Boolean result = withWaitFor(matcher).withTimeout(SECONDS.toMillis(1)).matches(ANY_OBJECT);
        verify(matcher, times(3)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void withTimeoutShouldNotOverrideCondition() {
        when(matcher.matches(any())).thenReturn(false);
        
        twoSecondsExpired.start();
        Boolean result = withWaitFor(matcher).orUntil(twoSecondsExpired).withTimeout(SECONDS.toMillis(5)).matches(ANY_OBJECT);
        verify(matcher, times(5)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void timeoutConditionShouldNotStartImmediately() throws InterruptedException {
        when(matcher.matches(any())).thenReturn(false);
        
        Matcher<? super Object> withWaitForMatcher = withWaitFor(matcher).withTimeout(SECONDS.toMillis(1));
        Thread.sleep(SECONDS.toMillis(2));
        Boolean result = withWaitForMatcher.matches(ANY_OBJECT);
        verify(matcher, times(3)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void latestConditionsShouldOverrideEarrliest() {
        when(matcher.matches(any())).thenReturn(false);
        
        TimeoutCondition oneSecondExpired = new TimeoutCondition(SECONDS.toMillis(1));
        oneSecondExpired.start();
        twoSecondsExpired.start();
        Boolean result = withWaitFor(matcher).orUntil(oneSecondExpired).orUntil(twoSecondsExpired).matches(ANY_OBJECT);
        verify(matcher, times(5)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
}

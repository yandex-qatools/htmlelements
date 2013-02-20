package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static ru.yandex.qatools.htmlelements.matchers.WaitForMatcherDecorator.withWaitFor;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 10.01.13
 * Time: 19:39
 */
public class WaitForMatcherTest {

    public static final Object ANY_OBJECT = "";

    @Test(expected = AssertionError.class)
    public void decoratorShouldThrowAssertionException() {
        assertThat(true, withWaitFor(equalTo(false)).withTimeout(SECONDS.toMillis(2)));
    }

    @Test
    public void decoratorShouldTryWhileGetTrue() throws Exception {
        Matcher<Object> matcher = mock(Matcher.class);
        when(matcher.matches(any())).thenReturn(false, false, true);

        assertThat(new Object(), withWaitFor(matcher));
        verify(matcher, times(3)).matches(any());
    }

    @Test
    public void decShouldTryWhileTimerGoesOut() throws Exception {
        Matcher<Object> matcher = mock(Matcher.class);
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
    	Matcher<Object> matcher = mock(Matcher.class);
        when(matcher.matches(any())).thenReturn(false);
        
        final long start = System.currentTimeMillis();
        Condition condition = new Condition() {
			@Override
			public boolean isTrue() {
				return System.currentTimeMillis() - start < 2000;
			}
		};
        Boolean result = withWaitFor(matcher).orUntil(condition).matches(ANY_OBJECT);
        
        verify(matcher, times(5)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void withTimeOut() {
    	Matcher<Object> matcher = mock(Matcher.class);
        when(matcher.matches(any())).thenReturn(false);
        
        Boolean result = withWaitFor(matcher).withTimeout(SECONDS.toMillis(1)).matches(ANY_OBJECT);
        verify(matcher, times(3)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void withTimeoutShouldNotOverrideCondition() {
    	Matcher<Object> matcher = mock(Matcher.class);
        when(matcher.matches(any())).thenReturn(false);
        
        final long start = System.currentTimeMillis();
        Condition condition = new Condition() {
			@Override
			public boolean isTrue() {
				return System.currentTimeMillis() - start < 2000;
			}
		};
        Boolean result = withWaitFor(matcher).orUntil(condition).withTimeout(SECONDS.toMillis(5)).matches(ANY_OBJECT);
        verify(matcher, times(5)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void timeoutConditionShouldNotStartImmediately() throws InterruptedException {
    	Matcher<Object> matcher = mock(Matcher.class);
        when(matcher.matches(any())).thenReturn(false);
        
        Matcher<? super Object> withWaitForMatcher = withWaitFor(matcher).withTimeout(SECONDS.toMillis(1));
        Thread.sleep(SECONDS.toMillis(2));
        Boolean result = withWaitForMatcher.matches(ANY_OBJECT);
        verify(matcher, times(3)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
}

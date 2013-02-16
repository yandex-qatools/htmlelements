package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static ru.yandex.qatools.htmlelements.matchers.WaitForMatcherDecorator.withWait;
import static ru.yandex.qatools.htmlelements.matchers.WaitForMatcherDecorator.withWaitFor;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 10.01.13
 * Time: 19:39
 */
@RunWith(MockitoJUnitRunner.class)
public class WaitForMatcherTest {

    public static final Object ANY_OBJECT = "";

    @Mock
    private Matcher<Object> matcher;


    @Test(expected = AssertionError.class)
    public void decoratorShouldThrowAssertionException() {
        assertThat(true, withWaitFor(equalTo(false), SECONDS.toMillis(2)));
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

        Boolean result = withWaitFor(matcher, SECONDS.toMillis(2), MILLISECONDS.toMillis(500)).matches(ANY_OBJECT);
        // 4 for check + return = 5
        verify(matcher, times(5)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }


    @Test
    public void theCaseWhenWeKnowShouldNotWaitOnlyNow() throws Exception {
        assertThat(withWait(matcher).dontWait(ifErrorOccurred()), not(instanceOf(WaitForMatcherDecorator.class)));
    }


    private static boolean ifErrorOccurred() {
        return true; // God says 'dont wait, son!', and he don't
    }
}

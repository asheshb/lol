package com.bitwindow.lol;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

public class MyEspressoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;

    public MyEspressoTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @Test
    public void testJokeFetchTask(){

        mActivity.setConnectedTestingAsync(true);
        onView(withId(R.id.jokeButton)).perform(click());
        assertNotNull(mActivity.mResult);

    }
}
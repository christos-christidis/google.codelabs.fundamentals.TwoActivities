package com.fundamentals.twoactivities;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

// SOS: Android fundamentals 06.1 suggests: Turn off all animation when testing w Espresso. Go to
// Developer Options > Drawing and turn off Window anim scale, Transition anim scale & Anim duration scale
@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {

    // SOS: must add androidTestImplementation 'com.android.support.test:rules:1.0.2' for this
    // I think this is necessary to manipulate MainActivity's views etc. There's also a ServiceTestRule..
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    // SOS: Espresso has ViewMatchers to find views, ViewActions to click or type text in them, and
    // ViewAssertions to assert stuff about them later
    @Test
    public void activityLaunch() {
        onView(withId(R.id.button_main)).perform(click());
        onView(withId(R.id.text_header)).check(matches(isDisplayed()));

        onView(withId(R.id.button_second)).perform(click());
        onView(withId(R.id.text_header_reply)).check(matches(isDisplayed()));
    }

    @Test
    public void textInputOutput() {
        onView(withId(R.id.editText_main)).perform(typeText("This is a test."));
        onView(withId(R.id.button_main)).perform(click());
        onView(withId(R.id.text_message)).check(matches(withText("This is a test.")));
    }

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.fundamentals.twoactivities", appContext.getPackageName());
    }
}

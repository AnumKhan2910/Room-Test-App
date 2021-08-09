package com.example.roomtestapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }


    @Test
    fun test_isRecyclerViewVisible() {
        onView(withId(R.id.btn)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_isTextCorrect() {
        onView(withId(R.id.btn)).check(matches(withText("Add")))
    }

    /*@Test
    fun test_navigationToSecondaryActivity() {
        onView(withId(R.id.btn)).perform(click())
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
    }*/

    @Test
    fun test_navigateToMainActivity() {
        onView(withId(R.id.btn)).perform(click())
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))

        //onView(withId(R.id.button)).perform(click())
        pressBack()
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }


    @Test
    fun test_ifItemAdded(){

        onView(withId(R.id.editText)).perform(typeText("Engineer"))
        onView(withId(R.id.editText)).check(matches(withText("Engineer")))
        onView(withId(R.id.btn)).perform(click())

    }

}
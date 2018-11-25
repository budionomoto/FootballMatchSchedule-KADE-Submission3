package id.xyzsystem.budiono.mission3jumat

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import id.xyzsystem.budiono.mission3jumat.R.id.listEvent2
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var ActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecylerViewBehaviour(){
        onView(withId(listEvent2)).check(matches(isDisplayed()))

        onView(withId(listEvent2)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(withId(listEvent2)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

    }
}


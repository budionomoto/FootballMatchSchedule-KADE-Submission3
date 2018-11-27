package id.xyzsystem.budiono.mission3jumat

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import id.xyzsystem.budiono.mission3jumat.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var ActivityRule = ActivityTestRule(MainActivity::class.java)

    /* Behaviour RecylcerView */

    @Test
    fun testRecylerViewBehaviour(){
        /* memastikan recyclerView dengan id 'listEvent2' ditampilkan */
        onView(withId(listEvent2)).check(matches(isDisplayed()))

        /* melakukan scroll pada recyclerview sampai dengan posisi ke-10*/
        onView(withId(listEvent2)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        /* klik item di posisi 10, untuk memastikan tampilkan detail*/
        onView(withId(listEvent2)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

    }

    /* Behaviour Prev. Match */
    @Test
    fun TestAppBehaviour(){

        /* 1) memastikan bahwa terdapat sebuah spinner yang ditampilkan */
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))

        /* 2) memastikan bahwa terdapat sebuah spinner yang ditampilkan */
        onView(withId(spinner2)).check(matches(isDisplayed()))

        /* 3) tindakan klik pada spinner */
        onView(withId(spinner2)).perform(click())

        /* 4) memberi tindakan klik pada spinner */
        onView(withText("Spanish La Liga")).perform(click())

        /* 5) melakukan scroll pada recyclerview sampai dengan posisi ke-10*/
        onView(withId(listEvent2)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        /* 6) klik item di posisi 10, untuk memastikan tampilkan detail*/
        onView(withId(listEvent2)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))


        /* 7) memastikan tombol favorite telah ditampilkan */
        onView(withId(add_to_favorite_id)).check(matches(isDisplayed()))

        /* 8) memberi tindakan klik pada tombol favorite */
        onView(withId(add_to_favorite_id)).perform(click())

        /* 9) memastikan teks 'Added to favorite' telah ditampilkan (snackbar)*/
//        onView(withText("Added to favorite")).check(matches(isDisplayed()))

        /* 10) menekan tombol kembali */
        pressBack()

        /* 11) memastikan sebuah 'BottomNavigationView' telah ditampilkan */
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))

        /* 12) memberikan tindakan klik pada sebuah menu favorite match di 'BottomNavigationView' */
        onView(withId(R.id.favoriteButtonId)).perform(click())

        /* 13) memberikan tindakan klik pada sebuah menu next match di 'BottomNavigationView' */
        onView(withId(nextButtonId)).perform(click())

        /* 14) memberikan tindakan klik pada sebuah menu previous match di 'BottomNavigationView' */
        onView(withId(previousButtonId)).perform(click())

    }
}


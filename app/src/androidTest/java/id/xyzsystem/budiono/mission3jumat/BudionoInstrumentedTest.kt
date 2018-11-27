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

    @Test
    fun testRecylerViewBehaviour(){
        /* memastikan recyclerView dengan id 'listEvent2' ditampilkan */
        onView(withId(listEvent2)).check(matches(isDisplayed()))

        /* melakukan scroll pada recyclerview sampai dengan posisi ke-10*/
        onView(withId(listEvent2)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        /* klik item di posisi 10, untuk memastikan tampilkan detail*/
        onView(withId(listEvent2)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

    }

    @Test
    fun TestAppBehaviour(){
        /* memastikan bahwa terdapat sebuah spinner yang ditampilkan */
        onView(withId(spinner2)).check(matches(isDisplayed()))

        /* memberi tindakan klik pada spinner */
        onView(withText("Spanish La Liga")).perform(click())

        /* memberi tindakan klik pada sebuah teks yang ditentukan */
        onView(withText("Barcelona")).check(matches(isDisplayed()))

        /* memastikan bahwa suatu teks telah ditampilkan dan memberikan tindakan  klik pada teks tesebut */
        onView(withText("Barcelona")).perform(click())

        /* memastikan tombol favorite telah ditampilkan */
        onView(withId(add_to_favorite_id)).check(matches(isDisplayed()))

        /* memberi tindakan klik pada tombol favorite */
        onView(withId(add_to_favorite_id)).perform(click())

        /* memastikan teks 'Added to favorites' telah ditampilkan (snackbar)*/
        onView(withText("Added to favorites")).check(matches(isDisplayed()))

        /* menekan tombol kembali */
        pressBack()

        /* memastikan sebuah 'BottomNavigationView' telah ditampilkan */
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))

        /* memberikan tindakan klik pada sebuah menu di 'BottomNavigationView' */
        onView(withId(favoriteButtonId)).perform(click())

    }
}


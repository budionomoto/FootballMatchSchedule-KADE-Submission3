package id.xyzsystem.budiono.mission3jumat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.xyzsystem.budiono.mission3jumat.R.id.*
import id.xyzsystem.budiono.mission3jumat.favorite.FavoriteFragment
import id.xyzsystem.budiono.mission3jumat.match.MatchFragment
import id.xyzsystem.budiono.mission3jumat.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

var snextMatch: String = "previous"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.previousButtonId -> {
                    loadPreviousMatchFragment(savedInstanceState)
                }
                nextButtonId -> {
                    loadNextMatchFragment(savedInstanceState)
                }
                favoriteButtonId -> {
                    loadFavoritesMatchFragment(savedInstanceState)
                }
                teamsButtonId -> {
                    loadTeamsFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.previousButtonId
    }

    private fun loadPreviousMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            //toast("Prev. Match ").show()
            //MatchFragment().nm("previous")
            snextMatch = "previous"

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    MatchFragment(), MatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            //toast("Next Match ").show()
            //MatchFragment().nm("next" )
            snextMatch = "next"

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    MatchFragment(), MatchFragment::class.java.simpleName)
                .commit()

        }

    }

    private fun loadFavoritesMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            //toast("Favorites").show()

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()

        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

}
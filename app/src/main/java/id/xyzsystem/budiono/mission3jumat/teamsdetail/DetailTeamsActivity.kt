package id.xyzsystem.budiono.mission3jumat.teamsdetail

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import id.xyzsystem.budiono.mission3jumat.R
import id.xyzsystem.budiono.mission3jumat.R.color.colorAccent
import id.xyzsystem.budiono.mission3jumat.R.id.center_horizontal
import id.xyzsystem.budiono.mission3jumat.model.LookupTeamModel
import id.xyzsystem.budiono.mission3jumat.utils.invisible
import id.xyzsystem.budiono.mission3jumat.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository

class DetailTeamsActivity : AppCompatActivity(), LookupTeamView {
    private lateinit var progressBar: ProgressBar
    private lateinit var model: LookupTeamModel
    private lateinit var id: String

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var TeamLogoIV: ImageView
    private lateinit var TeamNameTV: TextView
    private lateinit var FormedYearTV: TextView
    private lateinit var StadiumTV: TextView
    private lateinit var DescriptionENTV: TextView

    private lateinit var presenter: LookupTeamPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_teams)

        val intent = intent
        id = intent.getStringExtra("id")

        supportActionBar?.title = "Team Detail"
        /* back button */
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        linearLayout{
            lparams(width = matchParent, height = wrapContent)
            padding = dip(16)
            backgroundColor = Color.WHITE
            orientation = LinearLayout.VERTICAL

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                scrollView {
                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER

                        /* logo */
                        TeamLogoIV = imageView {}.lparams(
                            height = dip(60), width = dip(60)
                        )
                        /* Name */
                        TeamNameTV = textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textSize = 20f
                        }.lparams {
                            width = matchParent
                            height = wrapContent
                        }
                        TeamNameTV.setTypeface(null, Typeface.BOLD)
                        /* Year */
                        FormedYearTV = textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textSize = 16f
                        }.lparams {
                            width = matchParent
                            height = wrapContent
                        }
                        FormedYearTV.setTypeface(null, Typeface.BOLD)

                        /* Stadiun */
                        StadiumTV = textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textSize = 16f
                        }.lparams {
                            width = matchParent
                            height = wrapContent
                        }
                        StadiumTV.setTypeface(null, Typeface.BOLD)

                        /* Overview */
                        DescriptionENTV = textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textSize = 16f
                        }.lparams {
                            width = matchParent
                            height = wrapContent
                        }

                        progressBar = progressBar {
                        }.lparams {
                            center_horizontal
                            //centerHorizontally()
                        }
                    }
                }
            }

        }
        val request = ApiRepository()
        val gson = Gson()

        presenter = LookupTeamPresenter(this, request, gson)
        presenter.LookupTeam(id)

        swipeRefresh.onRefresh {
            presenter.LookupTeam(id)
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLookupTeamList(data: List<LookupTeamModel>) {
        model = LookupTeamModel(
            data[0].teamId,
            data[0].teamBadgeStr,
            data[0].teamStr,
            data[0].formedYearInt,
            data[0].stadiumStr,
            data[0].descriptionENStr
        )

        swipeRefresh.isRefreshing = false

        Picasso.get().load(data[0].teamBadgeStr).into(TeamLogoIV)
        TeamNameTV.text = data[0].teamStr
        FormedYearTV.text = data[0].formedYearInt
        StadiumTV.text = data[0].stadiumStr
        DescriptionENTV.text = data[0].descriptionENStr

    }

    /* menu back and favorite */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu

        setFavorite()

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite_id -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun removeFromFavorite() {

    }

    private fun addToFavorite(){

    }

    /* drawable icon */
    private fun setFavorite(){
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_added_to_favorites
            )
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_add_to_favorites
            )
    }

}


package id.xyzsystem.budiono.mission3jumat.detail

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
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
import id.xyzsystem.budiono.mission3jumat.R.id.add_to_favorite_id
import id.xyzsystem.budiono.mission3jumat.R.id.center_horizontal
import id.xyzsystem.budiono.mission3jumat.R.string.*
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository
import id.xyzsystem.budiono.mission3jumat.db.database
import id.xyzsystem.budiono.mission3jumat.model.Favorite
import id.xyzsystem.budiono.mission3jumat.model.MatchDetail
import id.xyzsystem.budiono.mission3jumat.model.Team
import id.xyzsystem.budiono.mission3jumat.utils.formatStdTanggal
import id.xyzsystem.budiono.mission3jumat.utils.invisible
import id.xyzsystem.budiono.mission3jumat.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.progressBar
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DetailActivity : AppCompatActivity(), MatchDetailView {

    private lateinit var modelTeamDetail: Team

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var timPresenter: TeamDetailPresenter

    private lateinit var modelMatchDetail: MatchDetail //model

    private lateinit var progressBar: ProgressBar //ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var dateEventTV: TextView
    private lateinit var homeTeamTV: TextView
    private lateinit var homeScoreTV: TextView

    private lateinit var awayTeamTV: TextView
    private lateinit var awayScoreTV: TextView

    private lateinit var homeLogoIV: ImageView
    private lateinit var awayLogoIV: ImageView

    private lateinit var homeGoalsTV: TextView
    private lateinit var awayGoalsTV: TextView

    private lateinit var homeShotsTV: TextView
    private lateinit var awayShotsTV: TextView

    private lateinit var homeKeeperTV: TextView
    private lateinit var awayKeeperTV: TextView

    private lateinit var homeDefenseTV: TextView
    private lateinit var awayDefenseTV: TextView

    private lateinit var homeMidfieldTV: TextView
    private lateinit var awayMidfieldTV: TextView

    private lateinit var homeForwardTV: TextView
    private lateinit var awayForwardTV: TextView

    private lateinit var homeSubtitutesTV: TextView
    private lateinit var awaySubtitutesTV: TextView

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var id2: String
    private lateinit var homeid2: String
    private lateinit var awayid2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        id2 = intent.getStringExtra("id")
        homeid2 = intent.getStringExtra("homeid")
        awayid2 = intent.getStringExtra("awayid")

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        linearLayout {
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

                        dateEventTV = textView {
                            textColor = Color.BLUE
                            padding = dip(16)
                            textAlignment = View.TEXT_ALIGNMENT_CENTER

                        }.lparams {
                            width = matchParent
                            height = wrapContent
                        }
                        dateEventTV.setTypeface(null, Typeface.BOLD)

                        linearLayout {
                            weightSum = 7f

                            homeLogoIV = imageView {}.lparams(height = dip(60), width = dip(60), weight = 2f)

                            homeScoreTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textSize = 50f
                            }.lparams {
                                weight = 1f
                            }
                            homeScoreTV.setTypeface(null, Typeface.BOLD)

                            textView {
                                setText(R.string.verusString2)
                                textSize = 20f
                                textColor = Color.BLUE
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                            }

                            awayScoreTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textSize = 50f
                            }.lparams {
                                weight = 1f
                            }
                            awayScoreTV.setTypeface(null, Typeface.BOLD)

                            awayLogoIV = imageView {}.lparams(height = dip(60), width = dip(60), weight = 2f)

                        }.lparams(
                            width = matchParent, height = wrapContent
                        )

                        /* team */
                        linearLayout {
                            weightSum = 3f

                            homeTeamTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textSize = 20f
                                textColor = Color.BLUE
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                            textView("").lparams {
                                weight = 1f
                                width = dip(0)
                            }
                            awayTeamTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textSize = 20f
                                textColor = Color.BLUE
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                        }.lparams(
                            width = matchParent, height = wrapContent
                        )

                        /* Goals */
                        linearLayout {
                            padding = dip(16)
                            weightSum = 3f

                            homeGoalsTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                            textView {
                                setText (GoalsString)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textColor = Color.BLUE
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }.setTypeface (null, Typeface.BOLD)

                            awayGoalsTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                        }.lparams(
                            width = matchParent, height = wrapContent
                        )

                        /* Shots */
                        linearLayout {
                            padding = dip(16)
                            weightSum = 3f

                            homeShotsTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }

                            textView {
                                setText(R.string.shotsString)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textColor = Color.BLUE
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }.setTypeface (null, Typeface.BOLD)

                            awayShotsTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                        }

                        /* lineups text */
                        textView {
                            setText(R.string.lineupsString)
                            textSize = 20f
                            padding = dip(20)
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }

                        /* Goal Keeper */
                        linearLayout {
                            bottomPadding = dip(16)
                            weightSum = 3f
                            lparams(width = matchParent, height = wrapContent)

                            homeKeeperTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                            textView {
                                setText(R.string.keeperString)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textColor = Color.BLUE
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }.setTypeface (null, Typeface.BOLD)

                            awayKeeperTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                        }

                        /* Defense */
                        linearLayout {
                            bottomPadding = dip(16)
                            weightSum = 3f
                            lparams(width = matchParent, height = wrapContent)

                            homeDefenseTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }

                            textView {
                                setText(R.string.defenseString)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textColor = Color.BLUE
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }.setTypeface (null, Typeface.BOLD)

                            awayDefenseTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                        }

                        /* Midfield */
                        linearLayout {
                            bottomPadding = dip(16)
                            weightSum = 3f
                            lparams(width = matchParent, height = wrapContent)

                            homeMidfieldTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                            textView {
                                setText(R.string.midfieldString)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textColor = Color.BLUE
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }.setTypeface (null, Typeface.BOLD)

                            awayMidfieldTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                        }
                        /* forward */
                        linearLayout {
                            bottomPadding = dip(16)
                            weightSum = 3f
                            lparams(width = matchParent, height = wrapContent)

                            homeForwardTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }

                            textView {
                                setText(R.string.forwardString)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textColor = Color.BLUE
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }.setTypeface (null, Typeface.BOLD)

                            awayForwardTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                        }

                        /* Subtitutes */
                        linearLayout {
                            bottomPadding = dip(16)
                            weightSum = 3f
                            lparams(width = matchParent, height = wrapContent)

                            homeSubtitutesTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }

                            textView {
                                setText(R.string.subtitutesString)
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textColor = Color.BLUE
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }.setTypeface (null, Typeface.BOLD)

                            awaySubtitutesTV = textView {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }.lparams {
                                weight = 1f
                                width = dip(0)
                            }
                        }

                        /* progress bar*/
                        progressBar = progressBar {
                        }.lparams {
                            center_horizontal
                            //centerHorizontally()
                        }
                    }
                }
            }
        }

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()

        presenter = MatchDetailPresenter(this, request, gson)
        presenter.getMatchDetail(id2)

        /* get team logo */
        timPresenter = TeamDetailPresenter(this, request, gson)
        timPresenter.getTeam(homeid2, true )
        timPresenter.getTeam(awayid2, false )

        swipeRefresh.onRefresh {
            presenter.getMatchDetail(id2)
            timPresenter.getTeam(homeid2,true)
            timPresenter.getTeam(awayid2, false )
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to id2)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    /* isi text Match */
    override fun showMatchDetail(data: List<MatchDetail>) {
        modelMatchDetail = MatchDetail(
            data[0].eventId,
            data[0].eventDate,
            data[0].homeTeam,
            data[0].awayTeam,
            data[0].homeScore,
            data[0].awayScore,
            data[0].homeGoals,
            data[0].awayGoals,
            data[0].homeShots,
            data[0].awayShots,
            data[0].homeKeeper,
            data[0].awayKeeper,
            data[0].homeDefense,
            data[0].awayDefense,
            data[0].homeMidfield,
            data[0].awayMidfield,
            data[0].homeForward,
            data[0].awayForward,
            data[0].homeSubtitutes,
            data[0].awaySubtitutes,
            data[0].homeTeam_id,
            data[0].awayTeam_id
        )

        swipeRefresh.isRefreshing = false

        //val formatter = SimpleDateFormat("EEEE, dd MMM yyyy", Locale("in"))
        //val setelahdiformat = formatter.format(data[0].eventDate)

        dateEventTV.text = formatStdTanggal(data[0].eventDate)//setelahdiformat.toString()

        homeScoreTV.text = if(data[0].homeScore.toString()=="null") "0" else data[0].homeScore.toString()
        awayScoreTV.text = if(data[0].awayScore.toString()=="null") "0" else data[0].awayScore.toString()

        homeTeamTV.text = data[0].homeTeam
        awayTeamTV.text = data[0].awayTeam

        homeGoalsTV.text = data[0].homeGoals
        awayGoalsTV.text = data[0].awayGoals

        homeShotsTV.text = if (data[0].homeShots.toString()=="null") "" else data[0].homeShots.toString()
        awayShotsTV.text = if (data[0].awayShots.toString()=="null") "" else data[0].awayShots.toString()

        homeKeeperTV.text = data[0].homeKeeper
        awayKeeperTV.text = data[0].awayKeeper

        homeDefenseTV.text = data[0].homeDefense
        awayDefenseTV.text = data[0].awayDefense

        homeMidfieldTV.text = data[0].homeMidfield
        awayMidfieldTV.text = data[0].awayMidfield

        homeForwardTV.text = data[0].homeForward
        awayForwardTV.text = data[0].awayForward

        homeSubtitutesTV.text = data[0].homeSubtitutes
        awaySubtitutesTV.text = data[0].awaySubtitutes
    }

    override fun showTeamDetail(datatim: List<Team>, homeOrAway: Boolean) {

        modelTeamDetail = Team(

            datatim[0].teamId,
            datatim[0].teamBadge
        )

        if (homeOrAway == true) {
            Picasso.get().load(datatim[0].teamBadge).into(homeLogoIV)
        } else{
            Picasso.get().load(datatim[0].teamBadge).into(awayLogoIV)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu

        setFavorite()

        //if (this::modelMatchDetail.isInitialized) return false else return true
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite_id -> {
                if (this::modelMatchDetail.isInitialized) {
                    //toast("koneksi").show()
                    if (isFavorite) removeFromFavorite() else addToFavorite()
                    isFavorite = !isFavorite
                }else{
                    toast("Koneksi Internet sedang lambat/tidak ada koneksi internet").show()
                }


                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun addToFavorite(){
        //val formatter = SimpleDateFormat("EEEE, dd MMM yyyy", Locale("in"))
        //val tgldiformat = formatter.format(modelMatchDetail.eventDate)
        //formatStdTanggal(modelMatchDetail.eventDate)

        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to modelMatchDetail.eventId,
                    Favorite.HOME_TEAM to modelMatchDetail.homeTeam,
                    Favorite.HOME_SCORE to modelMatchDetail.homeScore,
                    Favorite.AWAY_TEAM to modelMatchDetail.awayTeam,
                    Favorite.AWAY_SCORE to modelMatchDetail.awayScore,
                    Favorite.EVENT_DATE to formatStdTanggal(modelMatchDetail.eventDate),
                    Favorite.HOME_ID to modelMatchDetail.homeTeam_id,
                    Favorite.AWAY_ID to modelMatchDetail.awayTeam_id
                )
            }
            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to id2)
            }
            swipeRefresh.snackbar( "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
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
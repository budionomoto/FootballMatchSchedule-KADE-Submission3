package id.xyzsystem.budiono.mission3jumat.match

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import id.xyzsystem.budiono.mission3jumat.detail.DetailActivity
import id.xyzsystem.budiono.mission3jumat.R.array.arrayLeagueID
import id.xyzsystem.budiono.mission3jumat.R.array.arrayLeagueName
import id.xyzsystem.budiono.mission3jumat.R.id.listEvent2
import id.xyzsystem.budiono.mission3jumat.R.id.spinner2
import id.xyzsystem.budiono.mission3jumat.model.Match
import id.xyzsystem.budiono.mission3jumat.snextMatch
import id.xyzsystem.budiono.mission3jumat.utils.invisible
import id.xyzsystem.budiono.mission3jumat.utils.visible
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository
import id.xyzsystem.budiono.mission3jumat.R.color.colorAccent2
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class MatchFragment: Fragment(), AnkoComponent<Context>, MatchView {

    private var listMatch: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter
    private lateinit var spinner: Spinner
    private lateinit var listEventR: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var leagueName: String
    private lateinit var leagueID: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerid = resources.getStringArray(arrayLeagueID)

        val spinnerItems = resources.getStringArray(arrayLeagueName)
        val spinnerAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.adapter = spinnerAdapter

        adapter = MatchAdapter(listMatch) {
            context?.startActivity<DetailActivity>(
                "id" to "${it.eventId}",
                "homeid" to "${it.homeTeamId}",
                "awayid" to "${it.awayTeamId}"
            )
            //toast("klik").show()
        }

        listEventR.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                leagueID = spinnerid.get  (spinner.selectedItemPosition)

                //toast(leagueID).show()

                presenter.getMatchList(leagueID, snextMatch)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getMatchList(leagueID, snextMatch)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = spinner2
            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    colorAccent2,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listEventR = recyclerView {
                        id = listEvent2
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        listMatch.clear()
        listMatch.addAll(data)
        adapter.notifyDataSetChanged()
    }
}

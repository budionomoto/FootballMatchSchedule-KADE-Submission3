package id.xyzsystem.budiono.mission3jumat.teams

import android.R
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import id.xyzsystem.budiono.mission3jumat.R.array.arrayLeagueName
import id.xyzsystem.budiono.mission3jumat.R.color.colorAccent
import id.xyzsystem.budiono.mission3jumat.model.Teams
import id.xyzsystem.budiono.mission3jumat.teamsdetail.DetailTeamsActivity
import id.xyzsystem.budiono.mission3jumat.utils.invisible
import id.xyzsystem.budiono.mission3jumat.utils.visible
import id.xyzsystem.budiono.mission3jumat.R.id.spinner3
import org.jetbrains.anko.support.v4.onRefresh
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamsFragment: Fragment(), AnkoComponent<Context>, TeamsView {
    private var listTeams :MutableList<Teams> = mutableListOf()

    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: TeamsAdapter
    private lateinit var presenter: TeamsPresenter
    private lateinit var spinner: Spinner
    private lateinit var recyclerview : RecyclerView
    private lateinit var swiperefesh: SwipeRefreshLayout
    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val spinnerItems = resources.getStringArray(arrayLeagueName)
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_item, spinnerItems
        )

        spinner.adapter = spinnerAdapter

        adapter = TeamsAdapter(listTeams) {
            context?.startActivity<DetailTeamsActivity>(
                "id" to "${it.teamid}"
            )
            //toast("klik").show()
        }

        recyclerview.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                //toast(leagueID).show()

                presenter.getTeamsList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swiperefesh.onRefresh {
            presenter.getTeamsList(leagueName)
        }

    }

    override fun showTeamsList(data: List<Teams>) {
        swiperefesh.isRefreshing = false
        listTeams.clear()
        listTeams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = spinner3
            }

            swiperefesh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    recyclerview = recyclerView {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

}


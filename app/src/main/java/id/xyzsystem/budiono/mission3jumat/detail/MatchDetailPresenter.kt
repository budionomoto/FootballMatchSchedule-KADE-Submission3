package id.xyzsystem.budiono.mission3jumat.detail

import com.google.gson.Gson
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository
import id.xyzsystem.budiono.mission3jumat.api.TheSportDBApi
import id.xyzsystem.budiono.mission3jumat.model.MatchDetailResponse
import id.xyzsystem.budiono.mission3jumat.model.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson
) {

    fun getMatchDetail(evId: String) {
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(evId)),
                MatchDetailResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchDetail(data.events) /* get the responseDetail */
            }
        }
    }
}

class TeamDetailPresenter(private val view: MatchDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson
) {
    fun getTeam(teamId: String, homeOrAway: Boolean){
        view.showLoading()
        doAsync {
            val datatim = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeam(teamId)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamDetail(datatim.teams, homeOrAway )
            }
        }
    }
}

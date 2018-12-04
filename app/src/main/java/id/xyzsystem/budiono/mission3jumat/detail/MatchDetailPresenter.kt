package id.xyzsystem.budiono.mission3jumat.detail

import com.google.gson.Gson
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository
import id.xyzsystem.budiono.mission3jumat.api.TheSportDBApi
import id.xyzsystem.budiono.mission3jumat.model.MatchDetailResponse
import id.xyzsystem.budiono.mission3jumat.model.TeamResponse
import id.xyzsystem.budiono.mission3jumat.utils.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    /* sebelum penggunaan Coroutine */
    /*
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
    */
    /* sesudah menggunakan coroutine*/
    fun getMatchDetail(evId: String){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(evId)).await(),
                MatchDetailResponse::class.java
            )
            view.showMatchDetail(data.events)
            view.hideLoading()
        }
    }
}

class TeamDetailPresenter(private val view: MatchDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    /* sebelum penggunaan Coroutine */
    /*
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
    */

    /* sesudah penggunaan Coroutine */
    fun getTeam(teamId: String, homeOrAway: Boolean){
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val datatim = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeam(teamId)).await(),
                TeamResponse::class.java
            )
            view.showTeamDetail(datatim.teams,homeOrAway)
            view.hideLoading()
        }
    }
}

package id.xyzsystem.budiono.mission3jumat.match

import com.google.gson.Gson
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository
import id.xyzsystem.budiono.mission3jumat.api.TheSportDBApi
import id.xyzsystem.budiono.mission3jumat.model.MatchResponse
import id.xyzsystem.budiono.mission3jumat.utils.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getMatchList(leagueID: String?,nextMatch:String?) {
        view.showLoading()

        /* sebelum coroutine */
        /*
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatch(leagueID, nextMatch)),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)

            }
        }
        */

        /* sesudah penggunaan coroutine */
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getMatch(leagueID,nextMatch)).await(),
                    MatchResponse::class.java
            )
            view.showMatchList(data.events)
            view.hideLoading()
        }
    }
}
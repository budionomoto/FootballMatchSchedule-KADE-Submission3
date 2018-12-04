package id.xyzsystem.budiono.mission3jumat.teams

import com.google.gson.Gson
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository
import id.xyzsystem.budiono.mission3jumat.api.TheSportDBApi
import id.xyzsystem.budiono.mission3jumat.model.TeamsResponse
import id.xyzsystem.budiono.mission3jumat.utils.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(private val view: TeamsView
                     , private val apiRepository: ApiRepository
                     , private val gson: Gson
                    ,private val context: CoroutineContextProvider = CoroutineContextProvider()
){
    fun getTeamsList(league: String ){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)).await(),
                TeamsResponse::class.java
            )
            view.showTeamsList(data.teams)
            view.hideLoading()
        }
    }
}
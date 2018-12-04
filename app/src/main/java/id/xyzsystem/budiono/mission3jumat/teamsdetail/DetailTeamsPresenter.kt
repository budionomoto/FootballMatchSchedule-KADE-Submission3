package id.xyzsystem.budiono.mission3jumat.teamsdetail

import com.google.gson.Gson
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository
import id.xyzsystem.budiono.mission3jumat.api.TheSportDBApi
import id.xyzsystem.budiono.mission3jumat.model.DetailTeamsResponse
import id.xyzsystem.budiono.mission3jumat.utils.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LookupTeamPresenter(private val view: LookupTeamView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun LookupTeam(teamId: String){
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val datatim = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.LookUpTeam(teamId)).await(),
                DetailTeamsResponse::class.java
            )
            view.showLookupTeamList(datatim.teams)
            view.hideLoading()
        }
    }
}

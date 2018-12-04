package id.xyzsystem.budiono.mission3jumat.teamsdetail

import id.xyzsystem.budiono.mission3jumat.model.LookupTeamModel

interface LookupTeamView{
    fun showLoading()
    fun hideLoading()
    fun showLookupTeamList(data: List<LookupTeamModel>)
}
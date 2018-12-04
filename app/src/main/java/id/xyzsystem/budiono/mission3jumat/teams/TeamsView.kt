package id.xyzsystem.budiono.mission3jumat.teams

import id.xyzsystem.budiono.mission3jumat.model.Teams

interface TeamsView{
    fun showLoading()
    fun hideLoading()
    fun showTeamsList(data: List<Teams>)
}

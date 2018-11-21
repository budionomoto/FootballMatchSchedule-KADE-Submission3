package id.xyzsystem.budiono.mission3jumat.detail

import id.xyzsystem.budiono.mission3jumat.model.MatchDetail
import id.xyzsystem.budiono.mission3jumat.model.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<MatchDetail>)
    fun showTeamDetail(datatim: List<Team>, homeOrAway: Boolean)
}


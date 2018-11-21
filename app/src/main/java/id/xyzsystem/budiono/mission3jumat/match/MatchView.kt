package id.xyzsystem.budiono.mission3jumat.match

import id.xyzsystem.budiono.mission3jumat.model.Match

interface MatchView{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}

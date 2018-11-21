package id.xyzsystem.budiono.mission3jumat.api

import android.net.Uri
import id.xyzsystem.budiono.mission3jumat.BuildConfig

object TheSportDBApi {
    fun getMatchDetail(matchId: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupevent.php")
            .appendQueryParameter("id", matchId)
            .build()
            .toString()
    }

    fun getMatch(leagueId: String?, nextMatch: String?): String{

        val FileAPIMatch : String
        FileAPIMatch = if(nextMatch == "next") "eventsnextleague.php" else "eventspastleague.php"

        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath(FileAPIMatch)
            .appendQueryParameter("id", leagueId) //4328
            .build()
            .toString()
    }

    fun getTeam(TeamId:String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", TeamId)
            .build()
            .toString()
    }

}
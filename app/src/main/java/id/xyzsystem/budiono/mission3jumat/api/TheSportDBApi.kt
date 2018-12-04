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
            .appendQueryParameter("id", matchId)  //441613
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

    /* informasi tentang team */
    fun getTeam(TeamId:String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php") //133604
            .appendQueryParameter("id", TeamId)
            .build()
            .toString()
    }

    /* daftar semua team dalam liga */
    fun getTeams(league: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("search_all_teams.php")
            .appendQueryParameter("l", league)
            .build()
            .toString()
    }

    /* informasi tentang team */
    fun LookUpTeam(TeamId:String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php") //133604
            .appendQueryParameter("id", TeamId)
            .build()
            .toString()
    }

}
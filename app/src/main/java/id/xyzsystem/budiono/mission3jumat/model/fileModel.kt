package id.xyzsystem.budiono.mission3jumat.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Match(
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("dateEvent")
    var dateEventStr: Date?,

    @SerializedName("strHomeTeam")
    var homeTeamStr: String? = null,

    @SerializedName("intHomeScore")
    var homeScoreInt: Int?,

    @SerializedName("strAwayTeam")
    var awayTeamStr: String? = null,

    @SerializedName("intAwayScore")
    var awayScoreInt: Int?,

    @SerializedName("idHomeTeam")
    var homeTeamId: String? = null,

    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null
)

data class Favorite(val id: Long?,
                    val eventId: String?,
                    val eventDate: String?,
                    val homeTeamStr: String?,
                    val homeScoreInt: Int?,
                    val awayTeamStr: String?,
                    val awayScoreInt: Int?,
                    val homeTeamId: String?,
                    val awayTeamId: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
    }
}

data class MatchDetail(
    @SerializedName("idEvent")
    var eventId:String?= null,

    @SerializedName("dateEvent")
    var eventDate: Date?,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: Int? ,

    @SerializedName("intAwayScore")
    var awayScore: Int?,

    @SerializedName("strHomeGoalDetails")
    var homeGoals: String? = null,

    @SerializedName("strAwayGoalDetails")
    var awayGoals: String? = null,

    @SerializedName("intHomeShots")
    var homeShots: Int?,

    @SerializedName("intAwayShots")
    var awayShots: Int?,

    //line up
    @SerializedName("strHomeLineupGoalkeeper")
    var homeKeeper: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayKeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var homeDefense: String? = null,

    @SerializedName("strAwayLineupDefense")
    var awayDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var homeMidfield: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var awayMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var homeForward: String? = null,

    @SerializedName("strAwayLineupForward")
    var awayForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var homeSubtitutes: String? = null,

    @SerializedName(    "strAwayLineupSubstitutes")
    var awaySubtitutes: String? = null,

    @SerializedName("idHomeTeam")
    var homeTeam_id:String? = null,

    @SerializedName("idAwayTeam")
    var awayTeam_id:String? = null

)


data class Team(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null
)

data class Teams(
    @SerializedName("idTeam")
    var teamid: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null

)

/*
    API: getTeam
* */
data class LookupTeamModel(
    @SerializedName ("idTeam") /* 133739*/
    var teamId: String? = null,

    @SerializedName ("strTeamBadge") /* logo */
    var teamBadgeStr: String? = null,

    @SerializedName ("strTeam") /* Barcelona */
    var teamStr: String? = null,

    @SerializedName ("intFormedYear") /* 1899 */
    var formedYearInt: String? = null,

    @SerializedName ("strStadium") /* camp nou */
    var stadiumStr: String? = null,

    @SerializedName ("strDescriptionEN") /* overview */
    var descriptionENStr: String? = null

)
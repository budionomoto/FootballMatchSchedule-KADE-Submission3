package id.xyzsystem.budiono.mission3jumat.favorite

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import id.xyzsystem.budiono.mission3jumat.R
import id.xyzsystem.budiono.mission3jumat.R.id.*
import id.xyzsystem.budiono.mission3jumat.model.Favorite
import org.jetbrains.anko.*

class FavoriteAdapter(private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            TeamUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                /* date event */
                textView {
                    id = dateEventIdR
                    textSize = 16f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = Color.BLUE
                }.lparams{
                    width= matchParent
                }.setTypeface (null, Typeface.BOLD)

                /*home team */
                linearLayout{
                    lparams(width = matchParent, height = wrapContent)
                    weightSum = 7f

                    /* home team */
                    textView {
                        id = homeTeamIdR
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams{
                        weight = 2f
                        width = dip(0)
                        height= wrapContent
                    }.setTypeface (null, Typeface.BOLD)

                    /* home score */
                    textView {
                        id = homeScoreIdR
                        textSize = 25f
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams{
                        //margin = dip(15)
                        weight = 1f
                        width = dip(0)
                        height= wrapContent
                    }

                    /* text VS */
                    textView {
                        setText(R.string.versusString)
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams{
                        weight = 1f
                        width = dip(0)
                        height= wrapContent
                    }

                    /* away score */
                    textView {
                        id = awayScoreIdR
                        textSize = 25f
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    }.lparams{
                        weight = 1f
                        width = dip(0)
                        height= wrapContent
                    }

                    /* away Team */
                    textView {
                        id = awayTeamIdR
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    }.lparams{
                        margin = dip(2)
                        weight = 2f
                        width = dip(0)
                        height= dip(50)
                    }.setTypeface (null, Typeface.BOLD)
                }
            }
        }
    }
}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val dateEventTV: TextView = view.find(R.id.dateEventIdR)
    private val homeTeamTV: TextView = view.find(R.id.homeTeamIdR)
    private val homeScoreTV: TextView = view.find(R.id.homeScoreIdR)
    private val awayTeamTV: TextView = view.find(R.id.awayTeamIdR)
    private val awayScoreTV: TextView = view.find(R.id.awayScoreIdR)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        dateEventTV.text = favorite.eventDate

        homeTeamTV.text = favorite.homeTeamStr
        homeScoreTV.text = if(favorite.homeScoreInt.toString()=="null") "0" else favorite.homeScoreInt.toString()
        /* versus */
        awayTeamTV.text = favorite.awayTeamStr
        awayScoreTV.text = if(favorite.awayScoreInt.toString()=="null") "0" else favorite.awayScoreInt.toString()

        itemView.setOnClickListener { listener(favorite) }
    }
}
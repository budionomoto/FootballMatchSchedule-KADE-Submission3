package id.xyzsystem.budiono.mission3jumat.match

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import id.xyzsystem.budiono.mission3jumat.R
import id.xyzsystem.budiono.mission3jumat.R.id.*
import id.xyzsystem.budiono.mission3jumat.R.string.versusString
import id.xyzsystem.budiono.mission3jumat.model.Match
import id.xyzsystem.budiono.mission3jumat.utils.formatStdTanggal
import org.jetbrains.anko.*

class MatchAdapter(private val listMatch: List<Match>, private val listener: (Match) -> Unit)
    : RecyclerView.Adapter<MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            MatchUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(listMatch[position], listener)
    }

    override fun getItemCount(): Int = listMatch.size

}

class MatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {


            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                //date
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
                    bottomPadding = dip(3)
                    //backgroundColor = Color.LTGRAY

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

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val dateEventTV: TextView = view.find(R.id.dateEventIdR)
    private val homeTeamTV: TextView = view.find(R.id.homeTeamIdR)
    private val homeScoreTV: TextView = view.find(R.id.homeScoreIdR)
    private val awayTeamTV: TextView = view.find(R.id.awayTeamIdR)
    private val awayScoreTV: TextView = view.find(R.id.awayScoreIdR)

    fun bindItem(varMatch: Match, listener: (Match) -> Unit) {
        dateEventTV.text = formatStdTanggal(varMatch.dateEventStr)

        homeTeamTV.text = varMatch.homeTeamStr
        homeScoreTV.text = if (varMatch.homeScoreInt.toString() == "null") "0" else varMatch.homeScoreInt.toString()
        /* versus */
        awayTeamTV.text = varMatch.awayTeamStr
        awayScoreTV.text = if (varMatch.awayScoreInt.toString() == "null") "0" else varMatch.awayScoreInt.toString()

        itemView.setOnClickListener { listener(varMatch) }
    }
}
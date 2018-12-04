package id.xyzsystem.budiono.mission3jumat.teams

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.xyzsystem.budiono.mission3jumat.model.Teams
import org.jetbrains.anko.*
import id.xyzsystem.budiono.mission3jumat.R.id.team_badge
import id.xyzsystem.budiono.mission3jumat.R.id.team_name

class TeamsAdapter(private  val listTeams : List<Teams>, private val listener: (Teams) -> Unit)
    : RecyclerView.Adapter<TeamsViewHolder>(){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamsViewHolder {
        return TeamsViewHolder(
            TeamsUI().createView(
                AnkoContext.create(p0.context, p0)
            )
        )
    }

    override fun getItemCount(): Int = listTeams.size

    override fun onBindViewHolder(p0: TeamsViewHolder, pos: Int) {
        p0.bindItem(listTeams[pos], listener)
    }

}

class TeamsViewHolder(view1: View): RecyclerView.ViewHolder(view1){

    private val teamBadge: ImageView = view1.find(team_badge)
    private val teamName: TextView = view1.find(team_name)

    fun bindItem(varTeams: Teams, listener: (Teams) -> Unit) {
        Picasso.get().load(varTeams.teamBadge).into(teamBadge)
        teamName.text = varTeams.teamName

        /* event click */
        itemView.setOnClickListener { listener(varTeams) }
    }
}

class TeamsUI : AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = team_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = team_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }
            }
        }
    }

}
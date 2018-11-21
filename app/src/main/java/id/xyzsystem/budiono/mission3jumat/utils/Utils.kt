package id.xyzsystem.budiono.mission3jumat.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun formatStdTanggal(tglnya: Date?):String {
    val formatter = SimpleDateFormat("EEEE, dd MMM yyyy", Locale("in"))
    //val tgldiformat = formatter.format(tglnya)

    return formatter.format(tglnya)
}
package id.xyzsystem.budiono.mission3jumat.utils

import android.view.View
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun formatStdTanggal(tglnya: Date?):String {
    val formatter = SimpleDateFormat("EEEE, dd MMM yyyy", Locale("in"))
    return formatter.format(tglnya)
}

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy {Dispatchers.Main }
}


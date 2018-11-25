package id.xyzsystem.budiono.mission3jumat.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class ApiRepository {
    /* fun sebelum coroutine*/
    /*
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
    */

    /* fun sesudah coroutine */
    fun doRequest(url: String): Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }
}
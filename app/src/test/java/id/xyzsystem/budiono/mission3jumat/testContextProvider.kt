package id.xyzsystem.budiono.mission3jumat

import id.xyzsystem.budiono.mission3jumat.utils.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

class TestContextProvider : CoroutineContextProvider() {
    @ExperimentalCoroutinesApi
    override val main: CoroutineContext = Unconfined //Dispatchers.Unconfined
}

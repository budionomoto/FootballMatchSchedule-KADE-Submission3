package id.xyzsystem.budiono.mission3jumat.match

import com.google.gson.Gson
import id.xyzsystem.budiono.mission3jumat.TestContextProvider
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository
import id.xyzsystem.budiono.mission3jumat.api.TheSportDBApi
import id.xyzsystem.budiono.mission3jumat.model.Match
import id.xyzsystem.budiono.mission3jumat.model.MatchResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`

import org.mockito.MockitoAnnotations

/**
 * membuat mock object: Kelas MatchView, Gson, dan ApiRepo
 * yang berguna untuk parameter kelas MatchPresenter
 */

class MatchPresenterTest {
    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    /* inisialisasi presenter */
    private lateinit var presenter : MatchPresenter

    @Before
    fun SetUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchList() {
        val leagueId = "4328"
        val nextMatch  = "previous"
        val Listmatch: MutableList<Match> = mutableListOf()
        val ServerResponse = MatchResponse(Listmatch)

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(
                        TheSportDBApi.getMatch(leagueId, nextMatch)).await()
                    , MatchResponse::class.java
                )
            ).thenReturn(ServerResponse)

            presenter.getMatchList(leagueId, nextMatch)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(Listmatch)
            Mockito.verify(view).hideLoading()
        }
    }
}
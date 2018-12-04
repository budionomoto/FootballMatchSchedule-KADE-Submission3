package id.xyzsystem.budiono.mission3jumat.match

import com.google.gson.Gson
import id.xyzsystem.budiono.mission3jumat.TestContextProvider
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository
import id.xyzsystem.budiono.mission3jumat.api.TheSportDBApi
import id.xyzsystem.budiono.mission3jumat.detail.MatchDetailPresenter
import id.xyzsystem.budiono.mission3jumat.detail.MatchDetailView
import id.xyzsystem.budiono.mission3jumat.model.MatchDetail
import id.xyzsystem.budiono.mission3jumat.model.MatchDetailResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {
    @Mock
    private
    lateinit var view: MatchDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    /* inisialisasi presenter */
    private lateinit var presenter : MatchDetailPresenter

    @Before
    fun SetUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchDetailList() {
        val EventId = "441613"
        val ListMatchDetail: MutableList<MatchDetail> = mutableListOf()
        val ServerResponse = MatchDetailResponse(ListMatchDetail)

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(
                        TheSportDBApi.getMatchDetail(EventId)
                    ).await()
                    , MatchDetailResponse::class.java
                )
            ).thenReturn(ServerResponse)

            presenter.getMatchDetail(EventId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchDetail(ListMatchDetail)
            Mockito.verify(view).hideLoading()
        }
    }
}
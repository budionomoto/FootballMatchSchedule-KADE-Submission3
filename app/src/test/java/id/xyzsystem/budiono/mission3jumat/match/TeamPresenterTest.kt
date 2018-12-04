package id.xyzsystem.budiono.mission3jumat.match

import com.google.gson.Gson
import id.xyzsystem.budiono.mission3jumat.TestContextProvider
import id.xyzsystem.budiono.mission3jumat.api.ApiRepository
import id.xyzsystem.budiono.mission3jumat.api.TheSportDBApi
import id.xyzsystem.budiono.mission3jumat.detail.MatchDetailPresenter
import id.xyzsystem.budiono.mission3jumat.detail.MatchDetailView
import id.xyzsystem.budiono.mission3jumat.detail.TeamDetailPresenter
import id.xyzsystem.budiono.mission3jumat.model.MatchDetail
import id.xyzsystem.budiono.mission3jumat.model.MatchDetailResponse
import id.xyzsystem.budiono.mission3jumat.model.Team
import id.xyzsystem.budiono.mission3jumat.model.TeamResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPresenterTest {
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
    private lateinit var presenter : TeamDetailPresenter

    @Before
    fun SetUp(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeamDetailList() {
        val TeamId = "133604"
        val TeamHome = true
        val ListTeamDetail: MutableList<Team> = mutableListOf()
        val ServerResponse = TeamResponse(ListTeamDetail)

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(
                        TheSportDBApi.getTeam(TeamId)
                    ).await()
                    , TeamResponse::class.java
                )
            ).thenReturn(ServerResponse)

            presenter.getTeam(TeamId,TeamHome)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamDetail(ListTeamDetail,TeamHome)
            Mockito.verify(view).hideLoading()
        }
    }
}

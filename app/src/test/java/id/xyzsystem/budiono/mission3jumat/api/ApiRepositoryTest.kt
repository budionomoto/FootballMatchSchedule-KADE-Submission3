package id.xyzsystem.budiono.mission3jumat.api

import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Test
    fun doRequest() {
        val apiRepository = mock(ApiRepository::class.java)
        //val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        apiRepository.doRequest(url)

        verify(apiRepository).doRequest(url)

    }
}
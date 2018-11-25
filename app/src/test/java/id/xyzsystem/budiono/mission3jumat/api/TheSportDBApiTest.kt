package id.xyzsystem.budiono.mission3jumat.api

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class TheSportDBApiTest {

    @Test
    fun getMatch() {
        val TheSportDBApia = mock(TheSportDBApi::class.java)

        TheSportDBApia.getMatch("4328","previous")
        verify(TheSportDBApia).getMatch("4328","previous")



    }

}
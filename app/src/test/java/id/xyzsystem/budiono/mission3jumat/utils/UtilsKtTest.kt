package id.xyzsystem.budiono.mission3jumat.utils

import org.jetbrains.anko.appcompatV7.R.id.message
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class UtilsKtTest {

    @Test
    fun formatStdTanggal() {
        val tgl = Date()
        println (formatStdTanggal(tgl))
    }
}
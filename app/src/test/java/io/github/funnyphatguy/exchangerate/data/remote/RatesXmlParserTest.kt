package io.github.funnyphatguy.exchangerate.data.remote

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDate

class RatesXmlParserTest {

    @Test
    fun parse() {
        val inputStream =
            requireNotNull(javaClass.classLoader?.getResourceAsStream("cbr_rates.xml"))

        val parser = RatesXmlParser()

        val result = parser.parse(inputStream)

        assertEquals(
            LocalDate.of(2026, 8, 1),
            result.date
        )

        assertEquals(
            2,
            result.currencies.size
        )

        val usd = result.currencies[0]
        assertEquals("R01235", usd.id)
        assertEquals("840", usd.numCode)
        assertEquals("USD", usd.charCode)
        assertEquals(1, usd.nominal)
        assertEquals("Доллар США", usd.name)
        assertEquals(BigDecimal("79.4637"), usd.value)
        assertEquals(BigDecimal("79.4637"), usd.vunitRate)

        val jpy = result.currencies[1]
        assertEquals("R01820", jpy.id)
        assertEquals("392", jpy.numCode)
        assertEquals("JPY", jpy.charCode)
        assertEquals(100, jpy.nominal)
        assertEquals("Иен", jpy.name)
        assertEquals(BigDecimal("49.4669"), jpy.value)
        assertEquals(BigDecimal("0.494669"), jpy.vunitRate)
    }
}

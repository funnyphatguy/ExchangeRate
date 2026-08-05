package io.github.funnyphatguy.exchangerate.data.remote

import io.github.funnyphatguy.exchangerate.data.remote.model.CurrencyRatesResponse
import io.github.funnyphatguy.exchangerate.data.remote.model.CurrencyResponse
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RatesXmlParser @Inject constructor() {

    private fun readCurrency(parser: XmlPullParser): CurrencyResponse {
        val id = requireNotNull(parser.getAttributeValue(null, "ID"))
        var numCode: String? = null
        var charCode: String? = null
        var nominal: Int? = null
        var name: String? = null
        var value: BigDecimal? = null
        var vunitRate: BigDecimal? = null

        var eventType = parser.next()

        while (eventType != XmlPullParser.END_TAG || parser.name != "Valute") {
            if (eventType == XmlPullParser.START_TAG) {
                when (parser.name) {
                    "NumCode" -> numCode = parser.nextText()
                    "CharCode" -> charCode = parser.nextText()
                    "Nominal" -> nominal = parser.nextText().toInt()
                    "Name" -> name = parser.nextText()
                    "Value" -> value = parser.nextText()
                        .replace(',', '.')
                        .toBigDecimal()

                    "VunitRate" -> vunitRate = parser.nextText()
                        .replace(',', '.')
                        .toBigDecimal()
                }
            }
            eventType = parser.next()
        }
        return CurrencyResponse(
            id = id,
            numCode = requireNotNull(numCode),
            charCode = requireNotNull(charCode),
            nominal = requireNotNull(nominal),
            name = requireNotNull(name),
            value = requireNotNull(value),
            vunitRate = requireNotNull(vunitRate)
        )
    }

    fun parse(inputStream: InputStream): CurrencyRatesResponse {
        val parser = XmlPullParserFactory.newInstance().newPullParser()
        parser.setInput(inputStream, null)

        val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        var date: LocalDate? = null
        val currencies = mutableListOf<CurrencyResponse>()

        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG && parser.name == "ValCurs") {
                val rawDate = requireNotNull(
                    parser.getAttributeValue(null, "Date")
                )
                date = LocalDate.parse(rawDate, dateFormatter)
            }

            if (eventType == XmlPullParser.START_TAG && parser.name == "Valute") {
                currencies.add(readCurrency(parser))
            }

            eventType = parser.next()
        }
        return CurrencyRatesResponse(
            date = requireNotNull(date),
            currencies = currencies
        )
    }
}

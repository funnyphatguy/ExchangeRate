package io.github.funnyphatguy.exchangerate.data.remote

import io.github.funnyphatguy.exchangerate.data.remote.model.CurrencyRatesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject

class RatesRemoteDataSource @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val parser: RatesXmlParser,
) {

    suspend fun loadRates(): CurrencyRatesResponse {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder().url(RATES_URL).get().build()

            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IOException(
                        "Не удалось загрузить курсы валют, код ошибки ${response.code}"
                    )
                }
                val body = response.body
                parser.parse(body.byteStream())
            }
        }
    }

    private companion object {

        const val RATES_URL = "https://www.cbr.ru/scripts/XML_daily.asp"

    }
}

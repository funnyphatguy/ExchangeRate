package io.github.funnyphatguy.exchangerate.data.remote.model

import java.time.LocalDate

data class CurrencyRatesResponse(
    val date: LocalDate,
    val currencies: List<CurrencyResponse>
)

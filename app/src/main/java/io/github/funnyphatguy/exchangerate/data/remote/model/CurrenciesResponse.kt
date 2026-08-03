package io.github.funnyphatguy.exchangerate.data.remote.model

import java.time.LocalDate

data class CurrenciesResponse(
    val date: LocalDate,
    val currencies: List<CurrencyDto>
) {
}
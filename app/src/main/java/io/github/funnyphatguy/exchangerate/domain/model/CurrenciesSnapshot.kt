package io.github.funnyphatguy.exchangerate.domain.model

import java.time.LocalDate

data class CurrenciesSnapshot(
    val date: LocalDate,
    val currencies: List<Currency>
)
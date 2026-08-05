package io.github.funnyphatguy.exchangerate.domain.model

import kotlinx.collections.immutable.ImmutableList
import java.time.LocalDate

data class CurrencyRates(
    val date: LocalDate,
    val currencies: ImmutableList<Currency>
)
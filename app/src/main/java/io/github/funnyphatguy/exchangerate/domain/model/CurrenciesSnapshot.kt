package io.github.funnyphatguy.exchangerate.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import java.time.LocalDate

@Immutable
data class CurrenciesSnapshot(
    val date: LocalDate,
    val currencies: ImmutableList<Currency>
)
package io.github.funnyphatguy.exchangerate.domain.model

import androidx.compose.runtime.Immutable
import java.math.BigDecimal

@Immutable
data class Currency(
    val code: String,
    val name: String,
    val rateInRubles: BigDecimal
)
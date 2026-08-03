package io.github.funnyphatguy.exchangerate.domain.model

import java.math.BigDecimal

data class Currency(
    val code: String,
    val name: String,
    val rateInRubles: BigDecimal
)
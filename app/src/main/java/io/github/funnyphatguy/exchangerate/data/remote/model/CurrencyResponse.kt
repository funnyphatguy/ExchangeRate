package io.github.funnyphatguy.exchangerate.data.remote.model

import java.math.BigDecimal

data class CurrencyResponse(
    val id: String,
    val numCode: String,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: BigDecimal,
    val vunitRate: BigDecimal
)

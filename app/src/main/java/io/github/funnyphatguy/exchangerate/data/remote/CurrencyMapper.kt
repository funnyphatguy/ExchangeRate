package io.github.funnyphatguy.exchangerate.data.remote

import io.github.funnyphatguy.exchangerate.data.remote.model.CurrencyDto
import io.github.funnyphatguy.exchangerate.domain.model.Currency

fun CurrencyDto.toDomain(): Currency = Currency(
    code = this.charCode,
    name = this.name,
    rateInRubles = this.vunitRate
)

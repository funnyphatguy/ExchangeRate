package io.github.funnyphatguy.exchangerate.data.repository

import io.github.funnyphatguy.exchangerate.data.database.CurrencyEntity
import io.github.funnyphatguy.exchangerate.data.remote.model.CurrencyRatesResponse
import io.github.funnyphatguy.exchangerate.data.remote.model.CurrencyResponse
import io.github.funnyphatguy.exchangerate.domain.model.Currency
import io.github.funnyphatguy.exchangerate.domain.model.CurrencyRates
import kotlinx.collections.immutable.toImmutableList

object CurrencyMapper {

    fun currencyRatesResponseToCurrencyRates(
        response: CurrencyRatesResponse,
    ): CurrencyRates {
        return CurrencyRates(
            date = response.date,
            currencies = response.currencies.map(
                CurrencyMapper::currencyResponseToCurrency
            ).toImmutableList(),
        )
    }

    private fun currencyResponseToCurrency(
        currency: CurrencyResponse,
    ): Currency {
        return Currency(
            code = currency.charCode,
            name = currency.name,
            rateInRubles = currency.vunitRate,
        )
    }

    fun currencyToEntity(
        currency: Currency,
    ): CurrencyEntity {
        return CurrencyEntity(
            code = currency.code,
            name = currency.name,
            rateInRubles = currency.rateInRubles.toString(),
        )
    }

    fun currencyEntityToCurrency(
        currency: CurrencyEntity,
    ): Currency {
        return Currency(
            code = currency.code,
            name = currency.name,
            rateInRubles = currency.rateInRubles.toBigDecimal(),
        )
    }

}

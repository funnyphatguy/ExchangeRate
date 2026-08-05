package io.github.funnyphatguy.exchangerate.data.repository

import io.github.funnyphatguy.exchangerate.data.local.CurrencyDb
import io.github.funnyphatguy.exchangerate.data.remote.model.CurrenciesResponse
import io.github.funnyphatguy.exchangerate.data.remote.model.CurrencyDto
import io.github.funnyphatguy.exchangerate.domain.model.CurrenciesSnapshot
import io.github.funnyphatguy.exchangerate.domain.model.Currency

object CurrencyMapper {

    fun currenciesDtoToDomain(
        response: CurrenciesResponse,
    ): CurrenciesSnapshot {
        return CurrenciesSnapshot(
            date = response.date,
            currencies = response.currencies.map(
                CurrencyMapper::currencyDtoToDomain
            ),
        )
    }

    private fun currencyDtoToDomain(
        currency: CurrencyDto,
    ): Currency {
        return Currency(
            code = currency.charCode,
            name = currency.name,
            rateInRubles = currency.vunitRate,
        )
    }

    fun currencyDomainToDb(
        currency: Currency,
    ): CurrencyDb {
        return CurrencyDb(
            code = currency.code,
            name = currency.name,
            rateInRubles = currency.rateInRubles.toString(),
        )
    }

    fun currencyDbToDomain(
        currency: CurrencyDb,
    ): Currency {
        return Currency(
            code = currency.code,
            name = currency.name,
            rateInRubles = currency.rateInRubles.toBigDecimal(),
        )
    }

}
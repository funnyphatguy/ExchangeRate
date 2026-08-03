package io.github.funnyphatguy.exchangerate.data.repository

import io.github.funnyphatguy.exchangerate.data.remote.model.CurrenciesResponse
import io.github.funnyphatguy.exchangerate.data.remote.toDomain
import io.github.funnyphatguy.exchangerate.domain.model.CurrenciesSnapshot

class CurrencyMapper {

    fun mapFromDto(response: CurrenciesResponse): CurrenciesSnapshot {
        return CurrenciesSnapshot(
            date = response.date,
            currencies = response.currencies.map { it.toDomain() }
        )
    }
}

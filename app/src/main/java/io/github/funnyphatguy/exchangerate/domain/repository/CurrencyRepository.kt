package io.github.funnyphatguy.exchangerate.domain.repository

import io.github.funnyphatguy.exchangerate.domain.model.CurrenciesSnapshot

interface CurrencyRepository {

    suspend fun getCurrencies(): CurrenciesSnapshot
}
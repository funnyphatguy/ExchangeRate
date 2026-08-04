package io.github.funnyphatguy.exchangerate.domain.repository

import io.github.funnyphatguy.exchangerate.domain.model.CurrenciesSnapshot
import io.github.funnyphatguy.exchangerate.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getCurrencies(): CurrenciesSnapshot

    fun observeFavorites(): Flow<List<Currency>>

    suspend fun addFavorite(currency: Currency)

    suspend fun removeFavorite(code: String)
}
package io.github.funnyphatguy.exchangerate.domain.repository

import io.github.funnyphatguy.exchangerate.domain.model.Currency
import io.github.funnyphatguy.exchangerate.domain.model.CurrencyRates
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getCurrencyRates(): CurrencyRates

    fun observeFavorites(): Flow<List<Currency>>

    suspend fun addFavorite(currency: Currency)

    suspend fun removeFavorite(code: String)
}
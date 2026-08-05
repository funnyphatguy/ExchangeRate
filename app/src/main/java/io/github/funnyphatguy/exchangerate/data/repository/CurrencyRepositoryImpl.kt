package io.github.funnyphatguy.exchangerate.data.repository

import io.github.funnyphatguy.exchangerate.data.database.CurrencyDao
import io.github.funnyphatguy.exchangerate.data.remote.RatesRemoteDataSource
import io.github.funnyphatguy.exchangerate.domain.model.Currency
import io.github.funnyphatguy.exchangerate.domain.model.CurrencyRates
import io.github.funnyphatguy.exchangerate.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RatesRemoteDataSource,
    private val currencyDao: CurrencyDao
) : CurrencyRepository {
    override suspend fun getCurrencyRates(): CurrencyRates {
        val response = remoteDataSource.loadRates()
        return CurrencyMapper.currencyRatesResponseToCurrencyRates(response)
    }

    override fun observeFavorites(): Flow<List<Currency>> {
        return currencyDao.observeFavorites()
            .map { currencies ->
                currencies.map(
                    CurrencyMapper::currencyEntityToCurrency
                )
            }
    }

    override suspend fun addFavorite(currency: Currency) {
        val currencyEntity = CurrencyMapper.currencyToEntity(currency)
        currencyDao.insert(currencyEntity)
    }

    override suspend fun removeFavorite(code: String) {
        currencyDao.delete(code)
    }


}

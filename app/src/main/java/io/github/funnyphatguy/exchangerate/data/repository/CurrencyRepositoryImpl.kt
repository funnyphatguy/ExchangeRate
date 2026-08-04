package io.github.funnyphatguy.exchangerate.data.repository

import io.github.funnyphatguy.exchangerate.data.local.CurrencyDao
import io.github.funnyphatguy.exchangerate.data.remote.RatesRemoteDataSource
import io.github.funnyphatguy.exchangerate.domain.model.CurrenciesSnapshot
import io.github.funnyphatguy.exchangerate.domain.model.Currency
import io.github.funnyphatguy.exchangerate.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RatesRemoteDataSource,
    private val currencyDao: CurrencyDao
) : CurrencyRepository {
    override suspend fun getCurrencies(): CurrenciesSnapshot {
        val response = remoteDataSource.loadRates()
        return CurrencyMapper.currenciesDtoToDomain(response)
    }

    override fun observeFavorites(): Flow<List<Currency>> {
        return currencyDao.observeFavorites()
            .map { currencies ->
                currencies.map(
                    CurrencyMapper::currencyDbToDomain
                )
            }
    }

    override suspend fun addFavorite(currency: Currency) {
        val currencyDb = CurrencyMapper.currencyDomainToDb(currency)
        currencyDao.insert(currencyDb)
    }

    override suspend fun removeFavorite(code: String) {
        currencyDao.delete(code)
    }


}
package io.github.funnyphatguy.exchangerate.data.repository

import io.github.funnyphatguy.exchangerate.data.remote.RatesRemoteDataSource
import io.github.funnyphatguy.exchangerate.domain.model.CurrenciesSnapshot
import io.github.funnyphatguy.exchangerate.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RatesRemoteDataSource,
    private val mapper: CurrencyMapper
) : CurrencyRepository {
    override suspend fun getCurrencies(): CurrenciesSnapshot {
        val response = remoteDataSource.loadRates()
        return mapper.mapFromDto(response)
    }


}
package io.github.funnyphatguy.exchangerate.presentation.rates

import io.github.funnyphatguy.exchangerate.domain.model.CurrenciesSnapshot
import java.time.LocalTime

sealed interface RatesUiState {
    data object Loading : RatesUiState
    data class Success(
        val snapshot: CurrenciesSnapshot,
        val isRefreshing: Boolean = false,
        val lastLoadedTime: LocalTime = LocalTime.now()
    ) : RatesUiState

    data class Error(val errorMessage: String) : RatesUiState
}
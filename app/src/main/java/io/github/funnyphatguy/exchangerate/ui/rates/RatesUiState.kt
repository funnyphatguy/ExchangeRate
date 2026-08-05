package io.github.funnyphatguy.exchangerate.ui.rates

import androidx.compose.runtime.Stable
import io.github.funnyphatguy.exchangerate.domain.model.CurrenciesSnapshot
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf
import java.time.LocalTime

@Stable
sealed interface RatesUiState {
    data object Loading : RatesUiState
    data class Success(
        val snapshot: CurrenciesSnapshot,
        val favorites: ImmutableSet<String> = persistentSetOf(),
        val isRefreshing: Boolean = false,
        val lastLoadedTime: LocalTime = LocalTime.now()
    ) : RatesUiState

    data class Error(val errorMessage: String) : RatesUiState
}

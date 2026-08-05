package io.github.funnyphatguy.exchangerate.ui.rates

import androidx.compose.runtime.Stable
import io.github.funnyphatguy.exchangerate.domain.model.CurrencyRates
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf
import java.time.LocalTime

data class RatesUiState(
    val screenState: RatesUiScreenState = RatesUiScreenState.Loading,
    val favorites: ImmutableSet<String> = persistentSetOf(),
)

@Stable
sealed interface RatesUiScreenState {
    data object Loading : RatesUiScreenState
    data class Success(
        val currencyRates: CurrencyRates,
        val favorites: ImmutableSet<String> = persistentSetOf(),
        val isRefreshing: Boolean = false,
        val lastLoadedTime: LocalTime = LocalTime.now()
    ) : RatesUiScreenState

    data class Error(val errorMessage: String) : RatesUiScreenState
}

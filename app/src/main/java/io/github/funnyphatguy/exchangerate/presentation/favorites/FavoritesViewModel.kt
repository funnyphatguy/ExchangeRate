package io.github.funnyphatguy.exchangerate.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.funnyphatguy.exchangerate.domain.model.Currency
import io.github.funnyphatguy.exchangerate.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    val uiState: StateFlow<FavoritesUiState> = repository.observeFavorites().map { favorites ->
        if (favorites.isEmpty()) {
            FavoritesUiState.Empty
        } else {
            FavoritesUiState.Content(
                favoriteCurrencies = favorites
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = STOP_TIME_MILLS),
        initialValue = FavoritesUiState.Loading
    )

    fun removeFavorite(currency: Currency) {
        viewModelScope.launch {
            repository.removeFavorite(currency.code)
        }
    }

    private companion object {
        const val STOP_TIME_MILLS = 5000L
    }
}
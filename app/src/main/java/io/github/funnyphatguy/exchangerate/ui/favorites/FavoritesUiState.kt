package io.github.funnyphatguy.exchangerate.ui.favorites

import androidx.compose.runtime.Stable
import io.github.funnyphatguy.exchangerate.domain.model.Currency
import kotlinx.collections.immutable.ImmutableList

@Stable
sealed interface FavoritesUiState {

    data object Loading : FavoritesUiState
    data object Empty : FavoritesUiState
    data class Content(
        val favoriteCurrencies: ImmutableList<Currency>
    ) : FavoritesUiState
}

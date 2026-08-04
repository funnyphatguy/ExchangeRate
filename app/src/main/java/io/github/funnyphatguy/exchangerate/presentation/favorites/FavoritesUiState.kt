package io.github.funnyphatguy.exchangerate.presentation.favorites

import io.github.funnyphatguy.exchangerate.domain.model.Currency

sealed interface FavoritesUiState {

    data object Loading : FavoritesUiState
    data object Empty : FavoritesUiState
    data class Content(
        val favoriteCurrencies: List<Currency>
    ) : FavoritesUiState
}
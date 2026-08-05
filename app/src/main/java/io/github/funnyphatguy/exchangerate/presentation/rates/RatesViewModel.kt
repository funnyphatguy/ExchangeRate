package io.github.funnyphatguy.exchangerate.presentation.rates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.funnyphatguy.exchangerate.domain.model.Currency
import io.github.funnyphatguy.exchangerate.domain.repository.CurrencyRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatesViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

   private var loadJob: Job? = null

    private var favoriteCodes: Set<String> = emptySet()

    private val _uiState: MutableStateFlow<RatesUiState> =
        MutableStateFlow(RatesUiState.Loading)

    val uiState: StateFlow<RatesUiState> = _uiState.asStateFlow()

    init {
        observeFavorites()
        loadRates()
    }


    private fun observeFavorites() {
        viewModelScope.launch {
            repository.observeFavorites().collect { favorites ->
                favoriteCodes = favorites.map { currency -> currency.code }.toSet()

                val currentState = _uiState.value

                if (currentState is RatesUiState.Success) {
                    _uiState.value = currentState.copy(favorites = favoriteCodes)
                }
            }
        }
    }

    fun toggleFavorites(currency: Currency) {
        val isFavorite = currency.code in favoriteCodes

        viewModelScope.launch {
            if (isFavorite) {
                repository.removeFavorite(currency.code)
            } else {
                repository.addFavorite(currency)
            }
        }
    }

    fun loadRates() {
        if (loadJob?.isActive == true) {
            return
        }

        val currentState = _uiState.value

       loadJob =  viewModelScope.launch {
            _uiState.value =
                if (currentState is RatesUiState.Success) {
                    currentState.copy(isRefreshing = true)
                } else {
                    RatesUiState.Loading
                }

            try {
                val result = repository.getCurrencies()

                _uiState.value = RatesUiState.Success(
                    snapshot = result,
                    isRefreshing = false,
                    favorites = favoriteCodes
                )
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.value = RatesUiState.Error(
                    errorMessage = "Не удалось загрузить курсы валют"
                )
            } finally {
                loadJob = null
            }
        }
    }
}
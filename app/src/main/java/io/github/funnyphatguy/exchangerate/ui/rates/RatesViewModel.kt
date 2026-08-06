package io.github.funnyphatguy.exchangerate.ui.rates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.funnyphatguy.exchangerate.domain.model.Currency
import io.github.funnyphatguy.exchangerate.domain.repository.CurrencyRepository
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class RatesViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private var loadJob: Job? = null

    private val _uiState = MutableStateFlow(RatesUiState())

    val uiState: StateFlow<RatesUiState> = _uiState.asStateFlow()

    init {
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            repository.observeFavorites().collect { favorites ->
                val favoriteCodes = favorites
                    .map(Currency::code)
                    .toImmutableSet()

                _uiState.update { currentState ->
                    currentState.copy(favorites = favoriteCodes)
                }
            }
        }
    }

    fun toggleFavorites(currency: Currency) {
        val isFavorite = currency.code in _uiState.value.favorites

        viewModelScope.launch {
            delay(200L.milliseconds)
            if (isFavorite) {
                repository.removeFavorite(currency.code)
            } else {
                repository.addFavorite(currency)
            }
        }
    }

    fun loadRates() {
        loadJob?.cancel()

        loadJob = viewModelScope.launch {
            _uiState.update { currentState ->
                when (currentState.screenState) {
                    RatesUiScreenState.Loading -> currentState

                    is RatesUiScreenState.Success,
                    is RatesUiScreenState.Error -> {
                        currentState.copy(isRefreshing = true)
                    }
                }
            }

            runCatching {
                repository.getCurrencyRates()
            }.onSuccess { currencyRates ->
                _uiState.update { currentState ->
                    currentState.copy(
                        screenState = RatesUiScreenState.Success(
                            currencyRates = currencyRates,
                        ),
                        isRefreshing = false,
                        lastLoadedTime = LocalTime.now(),
                    )
                }
            }.onFailure { exception ->
                if (exception is CancellationException) {
                    throw exception
                }

                _uiState.update { currentState ->
                    currentState.copy(
                        screenState = RatesUiScreenState.Error(
                            errorMessage = "Не удалось загрузить курсы валют",
                        ),
                        isRefreshing = false,
                    )
                }
            }
        }
    }
}

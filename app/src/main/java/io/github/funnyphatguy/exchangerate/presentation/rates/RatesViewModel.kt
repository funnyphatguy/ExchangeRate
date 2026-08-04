package io.github.funnyphatguy.exchangerate.presentation.rates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _uiState: MutableStateFlow<RatesUiState> =
        MutableStateFlow(RatesUiState.Loading)

    val uiState: StateFlow<RatesUiState> = _uiState.asStateFlow()

    init {
        loadRates()
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
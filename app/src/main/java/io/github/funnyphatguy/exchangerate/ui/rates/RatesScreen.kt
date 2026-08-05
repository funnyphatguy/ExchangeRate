package io.github.funnyphatguy.exchangerate.ui.rates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.funnyphatguy.exchangerate.common.withoutMillis
import io.github.funnyphatguy.exchangerate.domain.model.CurrenciesSnapshot
import io.github.funnyphatguy.exchangerate.domain.model.Currency
import io.github.funnyphatguy.exchangerate.ui.components.CurrencyCard
import kotlinx.collections.immutable.ImmutableSet

@Composable
fun RatesScreen(
    modifier: Modifier = Modifier,
    viewModel: RatesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RatesScreenContent(
        uiState = uiState,
        onRefresh = viewModel::loadRates,
        onFavoritePress = viewModel::toggleFavorites,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RatesScreenContent(
    uiState: RatesUiState,
    onRefresh: () -> Unit,
    onFavoritePress: (Currency) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isRefreshing =
        uiState is RatesUiState.Success &&
                uiState.isRefreshing

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier.fillMaxSize(),
    ) {
        when (uiState) {
            RatesUiState.Loading -> {
                LoadingContent()
            }

            is RatesUiState.Error -> {
                ErrorContent(
                    message = uiState.errorMessage
                )
            }

            is RatesUiState.Success -> {
                RatesContent(
                    snapshot = uiState.snapshot,
                    favorites = uiState.favorites,
                    lastLoadedTime = uiState.lastLoadedTime.withoutMillis(),
                    onFavoritePress = onFavoritePress
                )
            }
        }
    }
}

@Composable
private fun LoadingContent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent(
    message: String,
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = message, textAlign = TextAlign.Center)
    }
}

@Composable
private fun RatesContent(
    snapshot: CurrenciesSnapshot,
    favorites: ImmutableSet<String>,
    lastLoadedTime: String?,
    onFavoritePress: (Currency) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {

        Text(
            "Курсы валют",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant
        )

        Text(
            text = "Актуально на: ${snapshot.date} $lastLoadedTime",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = snapshot.currencies,
                key = { currency -> currency.code },
            ) { currency ->
                CurrencyCard(
                    currency = currency,
                    isFavorite = currency.code in favorites,
                    onFavoriteClick = onFavoritePress,
                )
            }
        }
    }
}



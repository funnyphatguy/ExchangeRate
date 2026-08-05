package io.github.funnyphatguy.exchangerate.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.funnyphatguy.exchangerate.R

enum class AppBottomBarDestination(
    val route: String,
    @StringRes val labelResId: Int,
    val icon: ImageVector,
) {
    RATES(
        route = "rates",
        labelResId = R.string.bottom_navigation_rates,
        icon = Icons.Default.AttachMoney
    ),

    FAVORITES(
        route = "favorites",
        labelResId = R.string.bottom_navigation_favorites,
        icon = Icons.Default.Star
    ),

    ABOUT(
        route = "about",
        labelResId = R.string.bottom_navigation_about,
        icon = Icons.Default.Person
    )
}

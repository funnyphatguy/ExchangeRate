package io.github.funnyphatguy.exchangerate.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppBottomBarDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    RATES(
        route = "rates",
        label = "Курсы",
        icon = Icons.Default.AttachMoney
    ),

    FAVORITES(
        route = "favorites",
        label = "Избранное",
        icon = Icons.Default.Star
    ),

    ABOUT(
        route = "about",
        label = "О себе",
        icon = Icons.Default.Person
    )
}

package io.github.funnyphatguy.exchangerate.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.funnyphatguy.exchangerate.ui.about.AboutScreen
import io.github.funnyphatguy.exchangerate.ui.favorites.FavoritesScreen
import io.github.funnyphatguy.exchangerate.ui.navigation.AppBottomBarDestination
import io.github.funnyphatguy.exchangerate.ui.navigation.BottomNavigationBar
import io.github.funnyphatguy.exchangerate.ui.rates.RatesScreen

@Composable
fun ExchangeRateApp() {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onDestinationClick = { destination ->
                    navController.navigate(destination.route) {
                        popUpTo(
                            navController.graph
                                .findStartDestination()
                                .id
                        ) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppBottomBarDestination.RATES.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(AppBottomBarDestination.RATES.route) {
                RatesScreen()
            }

            composable(AppBottomBarDestination.FAVORITES.route) {
                FavoritesScreen()
            }

            composable(AppBottomBarDestination.ABOUT.route) {
                AboutScreen()
            }
        }
    }
}

package io.github.funnyphatguy.exchangerate.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onDestinationClick: (AppBottomBarDestination) -> Unit,
) {
    Column {
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant
        )

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            AppBottomBarDestination.entries.forEach { destination ->
                val label = stringResource(id = destination.labelResId)
                NavigationBarItem(
                    selected = currentRoute == destination.route,
                    onClick = {
                        onDestinationClick(destination)
                    },
                    icon = {
                        Icon(
                            imageVector = destination.icon,
                            contentDescription = label
                        )
                    },
                    label = {
                        Text(
                            text = label,
                            fontSize = 14.sp,
                        )
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedTextColor =
                            MaterialTheme.colorScheme.primary,
                        unselectedTextColor =
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = Color.Transparent,
                    ),
                )
            }
        }
    }
}

package de.dannyb.imnuri.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import de.dannyb.imnuri.ext.hymnAppNavigationBarItemColors
import de.dannyb.imnuri.ui.screens.Screens

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<Screens>,
    selectedItem: State<NavBackStackEntry?>,
) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        tonalElevation = 4.dp,
    ) {
        val currentDestination = selectedItem.value?.destination?.route
        items.forEach { screen ->
            val (icon, text) = when (screen) {
                Screens.HymnsList -> Icons.Default.Home to "Hymns"
                Screens.Favorites -> Icons.Default.FavoriteBorder to "Favorites"
                Screens.Settings -> Icons.Default.Settings to "Settings"
                else -> Icons.Default.Home to "Hymns"
            }

            NavigationBarItem(
                icon = { Icon(icon, contentDescription = null) },
                label = { Text(text) },
                selected = currentDestination == screen.route,
                colors = NavigationBarItemDefaults.hymnAppNavigationBarItemColors(),
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}
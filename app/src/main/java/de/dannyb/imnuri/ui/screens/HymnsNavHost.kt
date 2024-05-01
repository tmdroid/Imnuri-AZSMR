package de.dannyb.imnuri.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.dannyb.imnuri.ui.components.BottomNavigationBar
import de.dannyb.imnuri.ui.screens.details.HymnDetailsScreen
import de.dannyb.imnuri.ui.screens.details.HymnDetailsViewModel
import de.dannyb.imnuri.ui.screens.hymns.HymnsListViewModel
import de.dannyb.imnuri.ui.screens.hymns.HymnsScreen
import de.dannyb.imnuri.ui.screens.settings.SettingsScreen
import de.dannyb.imnuri.ui.screens.settings.SettingsViewModel

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    val selectedNavigationItem = navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            if (selectedNavigationItem.value?.destination?.route != Screens.HymnDetails.route) {
                BottomNavigationBar(
                    navController = navController,
                    items = listOf(Screens.HymnsList, Screens.Favorites, Screens.Settings),
                    selectedItem = selectedNavigationItem,
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Screens.HymnsList.route,
        ) {
            composable(Screens.HymnsList.route) {
                val viewModel = hiltViewModel<HymnsListViewModel>()
                HymnsScreen(viewModel) {
                    val route = Screens.HymnDetails.route.replace("{number}", it.number.toString())
                    navController.navigate(route)
                }
            }
            composable(Screens.HymnDetails.route) {
                val viewModel = hiltViewModel<HymnDetailsViewModel>()
                val number = it.arguments?.getString("number")?.toInt() ?: 0
                HymnDetailsScreen(
                    viewModel = viewModel,
                    number = number,
                    onBackPressed = { navController.popBackStack() })
            }
            composable(Screens.Settings.route) {
                val viewModel = hiltViewModel<SettingsViewModel>()
                SettingsScreen(viewModel) {
                    navController.popBackStack()
                }
            }
            composable(Screens.Favorites.route) {
                Text(text = "Nothing here yet")
            }
            composable(Screens.Categories.route) {
                Text(text = "Nothing here yet")
            }
        }
    }
}

sealed class Screens(val route: String) {
    data object HymnsList : Screens("hymns_list")
    data object HymnDetails : Screens("hymn_details/{number}")
    data object Favorites : Screens("favorites")
    data object Categories : Screens("categories")
    data object Settings : Screens("settings")
}

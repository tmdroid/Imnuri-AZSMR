package de.dannyb.imnuri.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.dannyb.imnuri.ui.screens.details.HymnDetailsScreen
import de.dannyb.imnuri.ui.screens.details.HymnDetailsViewModel
import de.dannyb.imnuri.ui.screens.hymns.HymnsListViewModel
import de.dannyb.imnuri.ui.screens.hymns.HymnsScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.HymnsList.route) {
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
            HymnDetailsScreen(viewModel, number, onBackPressed = { navController.popBackStack() })
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

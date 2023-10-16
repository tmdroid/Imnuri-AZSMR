package de.dannyb.imnuri.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.dannyb.imnuri.ui.screens.hymns.HymnsScreen
import de.dannyb.imnuri.ui.screens.hymns.HymnsListViewModel

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
//            val viewModel = hiltViewModel<HymnsListViewModel>()
//            HymnDetailsScreen(viewModel)
        }
    }
}

sealed class Screens(val route: String) {
    object HymnsList : Screens("hymns_list")
    object HymnDetails : Screens("hymn_details/{number}")
    object Favorites : Screens("favorites")
    object Categories : Screens("categories")
    object Settings : Screens("settings")
}

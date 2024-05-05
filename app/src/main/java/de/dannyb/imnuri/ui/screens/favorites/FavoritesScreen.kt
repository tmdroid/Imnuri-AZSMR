package de.dannyb.imnuri.ui.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import de.dannyb.imnuri.ext.hymnsAppToolbarColors
import de.dannyb.imnuri.ui.screens.hymns.HymnListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onHymnSelected: (Int) -> Unit
) {
    val screenState by viewModel.getFavoriteHymns()
        .collectAsState(initial = FavoritesScreenState.Loading)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.hymnsAppToolbarColors(),
            title = { Text("Favorite Hymns") },
        )

        when (val state = screenState) {
            is FavoritesScreenState.Loading -> {
//            LoadingScreen()
            }

            is FavoritesScreenState.Success -> {
                HymnListScreen(
                    hymns = state.hymns,
                    onHymnClick = { hymn ->
                        onHymnSelected.invoke(hymn.number)
                    },
                    onFavoriteClick = { hymn -> viewModel.toggleFavorite(hymn) }
                )
            }

            is FavoritesScreenState.Error -> {
//            ErrorScreen(
//                message = state.message,
//                onRetry = { viewModel.getFavoriteHymns() }
//            )
            }
        }
    }
}
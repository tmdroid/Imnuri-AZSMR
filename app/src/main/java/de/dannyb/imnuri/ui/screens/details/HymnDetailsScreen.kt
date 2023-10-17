package de.dannyb.imnuri.ui.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import de.dannyb.imnuri.ui.components.ErrorMessage
import de.dannyb.imnuri.ui.components.LoadingIndicator
import de.dannyb.imnuri.ui.screens.details.components.LyricsScreen

@Composable
fun HymnDetailsScreen(
    viewModel: HymnDetailsViewModel,
    number: Int,
    onBackPressed: () -> Unit
) {
    val screenState = viewModel.state.collectAsState()
    viewModel.loadHymn(number)

    HymnDetailsContent(
        state = screenState.value,
        onRetryLoadHymn = { viewModel.loadHymn(number) },
        onBackPressed = onBackPressed,
        hymnDetailsOperations = viewModel
    )
}

@Composable
fun HymnDetailsContent(
    state: HymnDetailsScreenState,
    onRetryLoadHymn: () -> Unit,
    onBackPressed: () -> Unit,
    hymnDetailsOperations: HymnDetailsOperations,
) {
    if (state.hymn == null) {
        if (state.isLoading) {
            LoadingIndicator()
        } else {
            ErrorMessage(message = "Could not load hymn") { onRetryLoadHymn() }
        }
    } else {
        LyricsScreen(
            hymn = state.hymn,
            fontSize = state.zoom,
            onBackPressed = onBackPressed,
            operations = hymnDetailsOperations,
        )
    }
}

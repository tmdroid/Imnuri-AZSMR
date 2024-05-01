package de.dannyb.imnuri.ui.screens.hymns

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import de.dannyb.imnuri.domain.model.HymnModel

enum class HymnsToolbarState {
    NORMAL, SEARCH
}

data class HymnsScreenState(
    val hymns: List<HymnModel> = emptyList(),
    val toolbarState: MutableState<HymnsToolbarState> = mutableStateOf(HymnsToolbarState.NORMAL),
    val isLoading: Boolean = false,
    val query: String? = null,
)

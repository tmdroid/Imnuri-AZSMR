package de.dannyb.imnuri.ui.screens.hymns

import de.dannyb.imnuri.domain.model.HymnModel

enum class HymnsToolbarState {
    NORMAL, SEARCH
}

data class HymnsScreenState(
    val hymns: List<HymnModel> = emptyList(),
    val toolbarState: HymnsToolbarState = HymnsToolbarState.NORMAL,
    val isLoading: Boolean = false,
    val query: String? = null,
)

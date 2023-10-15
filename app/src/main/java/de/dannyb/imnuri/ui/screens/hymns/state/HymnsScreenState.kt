package de.dannyb.imnuri.ui.screens.hymns.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import de.dannyb.imnuri.domain.model.HymnModel

data class HymnsScreenState(
    val hymns: List<HymnModel> = emptyList(),
    val toolbarState: MutableState<String> = mutableStateOf("normal"),
    val isLoading: Boolean = false,
    val error: Throwable? = null
)

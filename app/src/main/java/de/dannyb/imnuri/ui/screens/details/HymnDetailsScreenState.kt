package de.dannyb.imnuri.ui.screens.details

import de.dannyb.imnuri.domain.model.HymnModel

data class HymnDetailsScreenState(
    val hymn: HymnModel? = null,
    val isLoading: Boolean = false,
    val zoom: Int,
)

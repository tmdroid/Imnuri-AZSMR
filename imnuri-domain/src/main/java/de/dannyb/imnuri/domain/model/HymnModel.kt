package de.dannyb.imnuri.domain.model

data class HymnModel(
    val number: Int,
    val title: String,
    val category: String,
    val key: String,
    val verses: List<String>,
    val hasMusicSheet: Boolean,
    val hasAudio: Boolean,
    val isFavorite: Boolean,
)

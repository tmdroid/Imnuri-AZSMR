package de.dannyb.imnuri.domain.usecase

import de.dannyb.imnuri.domain.model.HymnModel
import de.dannyb.imnuri.domain.repository.Preferences
import javax.inject.Inject

class ToggleFavoriteHymnUseCase @Inject constructor(
    private val preferences: Preferences,
) {
    fun execute(hymn: HymnModel) {
        if (hymn.isFavorite) {
            preferences.removeFavoriteHymn(hymn.number)
        } else {
            preferences.addFavoriteHymn(hymn.number)
        }
    }
}
package de.dannyb.imnuri.data.mapper

import de.dannyb.imnuri.data.local.entities.HymnEntity
import de.dannyb.imnuri.domain.model.HymnModel
import de.dannyb.imnuri.domain.repository.Preferences
import javax.inject.Inject

class HymnEntityToModelMapper @Inject constructor(
    private val preferences: Preferences
) {
    fun mapAll(hymns: List<HymnEntity>): List<HymnModel> {
        val favoriteHymns = preferences.favoriteHymns

        return hymns.map {
            HymnModel(
                number = it.number,
                title = it.title,
                category = it.category,
                key = it.key,
                verses = it.verses,
                hasMusicSheet = it.hasMusicSheet,
                hasAudio = it.hasMp3,
                isFavorite = favoriteHymns.contains(it.number),
            )
        }
    }

    fun map(hymn: HymnEntity): HymnModel {
        return mapAll(listOf(hymn)).first()
    }

}
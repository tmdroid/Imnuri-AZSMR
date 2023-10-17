package de.dannyb.imnuri.data.mapper

import de.dannyb.imnuri.data.local.entities.HymnEntity
import de.dannyb.imnuri.data.remote.dto.HymnDto
import javax.inject.Inject

class HymnDtoToEntityMapper @Inject constructor() {
    fun mapAll(hymns: List<HymnDto>): List<HymnEntity> {
        return hymns.map {
            HymnEntity(
                number = it.number,
                title = it.title,
                category = it.category,
                key = it.key,
                verses = it.verses,
                hasMusicSheet = it.hasMusicSheet,
                hasMp3 = it.hasMp3,
            )
        }
    }
}
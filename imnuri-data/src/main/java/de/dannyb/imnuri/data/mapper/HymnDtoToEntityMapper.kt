package de.dannyb.imnuri.data.mapper

import de.dannyb.imnuri.data.local.entities.HymnEntity
import de.dannyb.imnuri.data.remote.dto.HymnDto
import javax.inject.Inject

class HymnDtoToEntityMapper @Inject constructor(): DataClassMapper<HymnDto, HymnEntity> {

    override fun map(input: HymnDto): HymnEntity {
        return HymnEntity(
            number = input.number,
            title = input.title,
            category = input.category,
            key = input.key,
            verses = input.verses,
            hasMusicSheet = input.hasMusicSheet,
            hasMp3 = input.hasMp3,
        )
    }

}
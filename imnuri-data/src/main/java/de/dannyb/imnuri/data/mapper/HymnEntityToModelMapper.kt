package de.dannyb.imnuri.data.mapper

import de.dannyb.imnuri.data.local.entities.HymnEntity
import de.dannyb.imnuri.domain.model.HymnModel
import javax.inject.Inject

class HymnEntityToModelMapper @Inject constructor(): DataClassMapper<HymnEntity, HymnModel> {

    override fun map(input: HymnEntity): HymnModel {
        return HymnModel(
            number = input.number,
            title = input.title,
            category = input.category,
            key = input.key,
            verses = input.verses,
            hasMusicSheet = input.hasMusicSheet,
            hasAudio = input.hasMp3,
        )
    }

}
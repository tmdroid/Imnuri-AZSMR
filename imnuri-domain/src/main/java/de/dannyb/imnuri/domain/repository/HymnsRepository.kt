package de.dannyb.imnuri.domain.repository

import de.dannyb.imnuri.domain.model.HymnModel
import kotlinx.coroutines.flow.Flow

interface HymnsRepository {
    fun getAllHymns(): Flow<List<HymnModel>>
}
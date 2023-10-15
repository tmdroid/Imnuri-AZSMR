package de.dannyb.imnuri.domain.repository

import de.dannyb.imnuri.domain.model.HymnModel
import kotlinx.coroutines.flow.Flow

interface HymnsRepository {
    suspend fun getAllHymns(): List<HymnModel>
}
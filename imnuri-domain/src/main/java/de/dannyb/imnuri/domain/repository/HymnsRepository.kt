package de.dannyb.imnuri.domain.repository

import de.dannyb.imnuri.domain.model.HymnModel

interface HymnsRepository {
    suspend fun getAllHymns(query: String?): List<HymnModel>

    suspend fun getFavoriteHymns(): List<HymnModel>

    suspend fun getHymn(number: Int): HymnModel?
}
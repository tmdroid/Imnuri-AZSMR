package de.dannyb.imnuri.domain.usecase

import de.dannyb.imnuri.domain.model.HymnModel
import de.dannyb.imnuri.domain.repository.HymnsRepository
import javax.inject.Inject

class GetFavoriteHymnsUseCase @Inject constructor(private val repository: HymnsRepository) {
    suspend fun execute(): List<HymnModel> = repository.getFavoriteHymns()
}
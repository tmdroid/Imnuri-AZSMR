package de.dannyb.imnuri.domain.usecase

import de.dannyb.imnuri.domain.repository.HymnsRepository
import javax.inject.Inject

class GetHymnUseCase @Inject constructor(
    private val hymnsRepository: HymnsRepository
) {
    suspend fun execute(id: Int) = hymnsRepository.getHymn(id)
}
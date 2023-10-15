package de.dannyb.imnuri.domain.usecase

interface UseCase<T : Any> {
    suspend fun execute(): T
}
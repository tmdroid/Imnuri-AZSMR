package de.dannyb.imnuri.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.dannyb.imnuri.data.repository.HymnsRepositoryImpl
import de.dannyb.imnuri.domain.repository.HymnsRepository

@Module
@InstallIn(SingletonComponent::class)
internal abstract class InternalAppAppModule {

    @Binds
    abstract fun provideHymnsRepository(repository: HymnsRepositoryImpl): HymnsRepository

}
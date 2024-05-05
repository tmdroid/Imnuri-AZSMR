package de.dannyb.imnuri.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.dannyb.imnuri.data.pref.PreferencesImpl
import de.dannyb.imnuri.domain.repository.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InternalDataAppModule {

    @Binds
    @Singleton
    abstract fun bindPreferences(preferencesImpl: PreferencesImpl): Preferences
}
package de.dannyb.imnuri.di

import dagger.Component
import de.dannyb.imnuri.HymnsApp
import de.dannyb.imnuri.domain.di.DomainAppComponent

@Component(modules = [InternalAppAppModule::class])
interface AppAppComponent : DomainAppComponent {
    fun inject(app: HymnsApp)
}
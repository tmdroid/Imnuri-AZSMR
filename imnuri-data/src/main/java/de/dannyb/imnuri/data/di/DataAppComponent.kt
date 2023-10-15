package de.dannyb.imnuri.data.di

import dagger.Component
import de.dannyb.imnuri.domain.di.DomainAppComponent

@Component(modules = [DataAppModule::class], dependencies = [DomainAppComponent::class])
interface DataAppComponent {
}
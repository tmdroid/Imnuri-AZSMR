package de.dannyb.imnuri.data.di

import javax.inject.Singleton
import com.squareup.anvil.annotations.MergeComponent
import de.dannyb.imnuri.data.remote.ImnuriService

@MergeComponent(scope = Singleton::class, modules = [NetworkModule::class])
interface DataComponent {
    fun getImnuriService(): ImnuriService
}
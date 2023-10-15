package de.dannyb.imnuri.data.di

import javax.inject.Singleton
import com.squareup.anvil.annotations.MergeComponent

@MergeComponent(scope = Singleton::class)
interface DataComponent {
}
package de.dannyb.imnuri

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import de.dannyb.imnuri.di.AppAppComponent
import de.dannyb.imnuri.di.DaggerAppAppComponent

@HiltAndroidApp
class HymnsApp : Application() {

    lateinit var appAppComponent: AppAppComponent

    override fun onCreate() {
        super.onCreate()
        appAppComponent = DaggerAppAppComponent.create()
        appAppComponent.inject(this)
    }
}
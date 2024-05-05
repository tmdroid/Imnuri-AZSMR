package de.dannyb.imnuri.data.utils

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.dannyb.imnuri.data.pref.datastore.InMemoryDataStore
import de.dannyb.imnuri.data.pref.datastore.SharedPreferencesDataStore
import java.lang.ref.WeakReference

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface DataModuleDependencyProvider {
    fun getInMemoryDataStore(): InMemoryDataStore

    fun getSharedPreferencesDataStore(): SharedPreferencesDataStore

    fun getGson(): Gson
}

internal object DataModuleInjector {

    private var _contextRef: WeakReference<Context>? = null
    private val context: Context
        get() = _contextRef?.get()
            ?: throw IllegalStateException("DataModuleInjector not initialized")

    fun init(context: Context) {
        _contextRef = WeakReference(context)
    }

    private fun getDataModuleDependencyProvider(): DataModuleDependencyProvider =
        EntryPoints.get(context, DataModuleDependencyProvider::class.java)

    fun getInMemoryDataStore(): InMemoryDataStore =
        getDataModuleDependencyProvider().getInMemoryDataStore()

    fun getSharedPreferencesDataStore(): SharedPreferencesDataStore =
        getDataModuleDependencyProvider().getSharedPreferencesDataStore()

    fun getGson() = getDataModuleDependencyProvider().getGson()
}
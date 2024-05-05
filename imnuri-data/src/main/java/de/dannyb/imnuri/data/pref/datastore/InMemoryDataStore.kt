package de.dannyb.imnuri.data.pref.datastore

import androidx.preference.PreferenceDataStore
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

class InMemoryDataStore @Inject constructor() : PreferenceDataStore() {

    private val memoryMap by lazy { ConcurrentHashMap<String, Any>() }

    // Writes

    override fun putString(key: String, value: String?) = putValue(key, value)

    override fun putStringSet(key: String, values: Set<String>?) = putValue(key, values)

    override fun putInt(key: String, value: Int) = putValue(key, value)

    override fun putLong(key: String, value: Long) = putValue(key, value)

    override fun putFloat(key: String, value: Float) = putValue(key, value)

    override fun putBoolean(key: String, value: Boolean) = putValue(key, value)

    // Reads

    override fun getString(key: String, defValue: String?): String? = getValue(key) ?: defValue

    override fun getStringSet(key: String, defValues: Set<String>?): Set<String>? =
        getValue(key) ?: defValues

    override fun getInt(key: String, defValue: Int): Int = getValue(key) ?: defValue

    override fun getLong(key: String, defValue: Long): Long = getValue(key) ?: defValue

    override fun getFloat(key: String, defValue: Float): Float = getValue(key) ?: defValue

    override fun getBoolean(key: String, defValue: Boolean): Boolean = getValue(key) ?: defValue

    private fun putValue(key: String, value: Any?) {
        value?.let { memoryMap[key] = it } ?: memoryMap.remove(key)
    }

    private inline fun <reified T> getValue(key: String): T? = memoryMap[key] as? T

}
package de.dannyb.imnuri.data.pref.datastore

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferenceDataStore() {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(
            /* name = */ PREFS_NAME,
            /* mode = */ Context.MODE_PRIVATE
        )
    }

    // Writes
    override fun putString(key: String, value: String?) = sharedPreferences.edit(commit = true) {
        putString(key, value)
    }

    override fun putStringSet(key: String, values: Set<String>?) =
        sharedPreferences.edit(commit = true) {
            putStringSet(key, values)
        }

    override fun putInt(key: String, value: Int) = sharedPreferences.edit(commit = true) {
        putInt(key, value)
    }

    override fun putLong(key: String, value: Long) = sharedPreferences.edit(commit = true) {
        putLong(key, value)
    }

    override fun putFloat(key: String, value: Float) = sharedPreferences.edit(commit = true) {
        putFloat(key, value)
    }

    override fun putBoolean(key: String, value: Boolean) = sharedPreferences.edit(commit = true) {
        putBoolean(key, value)
    }

    // Reads
    override fun getString(key: String, defValue: String?): String? =
        sharedPreferences.getString(key, defValue)

    override fun getStringSet(key: String, defValues: Set<String>?): Set<String>? =
        sharedPreferences.getStringSet(key, defValues)

    override fun getInt(key: String, defValue: Int): Int = sharedPreferences.getInt(key, defValue)

    override fun getLong(key: String, defValue: Long): Long =
        sharedPreferences.getLong(key, defValue)

    override fun getFloat(key: String, defValue: Float): Float =
        sharedPreferences.getFloat(key, defValue)

    override fun getBoolean(key: String, defValue: Boolean): Boolean =
        sharedPreferences.getBoolean(key, defValue)

    companion object {
        private const val PREFS_NAME = "imnuri"
    }
}
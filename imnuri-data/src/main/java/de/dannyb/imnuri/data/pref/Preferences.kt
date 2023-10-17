package de.dannyb.imnuri.data.pref

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Preferences @Inject constructor(@ApplicationContext context: Context) {

    private val preferences by lazy {
        context.getSharedPreferences(
            /* name = */ PREFERENCE_NAME,
            /* mode = */ Context.MODE_PRIVATE
        )
    }

    var zoom: Int
        get() = preferences.getInt(KEY_ZOOM, DEFAULT_ZOOM)
        set(value) = preferences.edit { putInt(KEY_ZOOM, value) }

    var favoriteHymns: List<Int>
        get() = preferences.getStringSet(KEY_FAVORITE_HYMNS, DEFAULT_FAVORITE_HYMNS)
            ?.map { it.toInt() } ?: emptyList()
        private set(value) = preferences.edit {
            putStringSet(KEY_FAVORITE_HYMNS, value.map { it.toString() }.toSet())
        }

    fun addFavoriteHymn(number: Int) {
        favoriteHymns = favoriteHymns + number
    }

    fun removeFavoriteHymn(number: Int) {
        favoriteHymns = favoriteHymns - number
    }

    companion object {
        private const val PREFERENCE_NAME = "imnuri"

        private const val KEY_ZOOM = "zoom"
        private const val DEFAULT_ZOOM = 20

        private const val KEY_FAVORITE_HYMNS = "favorite_hymns"
        private val DEFAULT_FAVORITE_HYMNS = emptySet<String>()
    }
}
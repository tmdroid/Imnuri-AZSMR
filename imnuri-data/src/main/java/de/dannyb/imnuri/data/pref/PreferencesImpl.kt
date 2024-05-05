package de.dannyb.imnuri.data.pref

import com.google.gson.reflect.TypeToken
import de.dannyb.imnuri.domain.repository.Preferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesImpl @Inject constructor() : Preferences {

    override var zoom by IntPrefsDelegate(
        key = KEY_ZOOM,
        defaultValue = DEFAULT_ZOOM,
    )

    private val intListTypeToken = object : TypeToken<Set<Int>>() {}
    override var favoriteHymns by ObjectPrefsDelegate(
        key = KEY_FAVORITE_HYMNS,
        type = intListTypeToken.type,
        typeToken = intListTypeToken,
        defaultValue = DEFAULT_FAVORITE_HYMNS,
    )

    override fun addFavoriteHymn(number: Int) {
        favoriteHymns += number
    }

    override fun removeFavoriteHymn(number: Int) {
        favoriteHymns -= number
    }

    companion object {
        private const val KEY_ZOOM = "zoom"
        private const val DEFAULT_ZOOM = 20

        private const val KEY_FAVORITE_HYMNS = "favorite_hymns"
        private val DEFAULT_FAVORITE_HYMNS = emptySet<Int>()
    }
}
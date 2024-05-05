package de.dannyb.imnuri.domain.repository

interface Preferences {

    var zoom: Int

    var favoriteHymns: Set<Int>

    fun addFavoriteHymn(number: Int)

    fun removeFavoriteHymn(number: Int)
}
package de.dannyb.imnuri.ui.screens.details

interface HymnDetailsOperations {

    fun onZoomIncrement()

    fun onZoomDecrement()

    fun onZoomChanged(zoom: Int)

    fun onMusicSheetIconClicked()

    fun onAudioIconClicked()

}
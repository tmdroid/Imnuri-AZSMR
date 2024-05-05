package de.dannyb.imnuri.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.dannyb.imnuri.domain.repository.Preferences
import de.dannyb.imnuri.domain.usecase.GetHymnUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HymnDetailsViewModel @Inject constructor(
    private val getHymnUseCase: GetHymnUseCase,
    private val preferences: Preferences,
) : ViewModel(), HymnDetailsOperations {

    private val _state = MutableStateFlow(HymnDetailsScreenState(zoom = preferences.zoom))
    val state: StateFlow<HymnDetailsScreenState> get() = _state

    fun loadHymn(number: Int) = viewModelScope.launch(Dispatchers.IO) {
        _state.value = _state.value.copy(isLoading = true)
        val hymn = getHymnUseCase.execute(number)
        _state.value = _state.value.copy(hymn = hymn, isLoading = false)
    }

    override fun onZoomIncrement() {
        onZoomChanged(_state.value.zoom + ZOOM_INCREMENT_SIZE)
    }

    override fun onZoomDecrement() {
        onZoomChanged(_state.value.zoom - ZOOM_INCREMENT_SIZE)
    }

    override fun onZoomChanged(zoom: Int) {
        _state.update { it.copy(zoom = zoom) }
        preferences.zoom = zoom
    }

    override fun onMusicSheetIconClicked() {
    }

    override fun onAudioIconClicked() {
    }

    override fun onFavoriteIconClicked(isNowFavorite: Boolean) {
        val number = checkNotNull(_state.value.hymn).number
        if (isNowFavorite) {
            preferences.addFavoriteHymn(number)
        } else {
            preferences.removeFavoriteHymn(number)
        }
        loadHymn(number)
    }

    companion object {
        private const val ZOOM_INCREMENT_SIZE = 2
    }
}
package de.dannyb.imnuri.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.dannyb.imnuri.domain.usecase.GetHymnUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HymnDetailsViewModel @Inject constructor(
    private val getHymnUseCase: GetHymnUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HymnDetailsScreenState())
    val state: StateFlow<HymnDetailsScreenState> get() = _state

    fun loadHymn(number: Int) = viewModelScope.launch(Dispatchers.IO) {
        _state.value = _state.value.copy(isLoading = true)
        val hymn = getHymnUseCase.execute(number)
        _state.value = _state.value.copy(hymn = hymn, isLoading = false)
    }

    fun onZoomChanged(zoom: Int) {
        _state.value = _state.value.copy(zoom = zoom)
    }

    fun onMusicSheetIconClicked() {
    }

    fun onAudioIconClicked() {
    }

}
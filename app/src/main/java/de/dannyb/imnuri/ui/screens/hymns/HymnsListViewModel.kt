package de.dannyb.imnuri.ui.screens.hymns

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.dannyb.imnuri.domain.model.HymnModel
import de.dannyb.imnuri.domain.usecase.GetAllHymnsUseCase
import de.dannyb.imnuri.domain.usecase.ToggleFavoriteHymnUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HymnsListViewModel @Inject constructor(
    private val getAllHymnsUseCase: GetAllHymnsUseCase,
    private val toggleFavoriteHymnUseCase: ToggleFavoriteHymnUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow(HymnsScreenState())
    val screenState: StateFlow<HymnsScreenState> get() = _screenState

    fun getAllHymns(query: String? = null) = viewModelScope.launch(Dispatchers.IO) {
        _screenState.value = _screenState.value.copy(isLoading = true, query = query)

        val hymns = getAllHymnsUseCase.execute(query)
        withContext(Dispatchers.Main) {
            _screenState.value = _screenState.value.copy(hymns = hymns)
        }
    }

    fun toggleFavorite(hymn: HymnModel) = viewModelScope.launch(Dispatchers.IO) {
        toggleFavoriteHymnUseCase.execute(hymn)
        getAllHymns(_screenState.value.query)
    }

    fun onToolbarStateChanged(state: HymnsToolbarState) {
        _screenState.update { it.copy(toolbarState = state) }
    }
}
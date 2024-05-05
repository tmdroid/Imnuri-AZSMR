package de.dannyb.imnuri.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.dannyb.imnuri.domain.model.HymnModel
import de.dannyb.imnuri.domain.usecase.GetFavoriteHymnsUseCase
import de.dannyb.imnuri.domain.usecase.ToggleFavoriteHymnUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteHymnsUseCase: GetFavoriteHymnsUseCase,
    private val toggleFavoriteHymnUseCase: ToggleFavoriteHymnUseCase,
) : ViewModel() {

    private val _screenState: MutableStateFlow<FavoritesScreenState> =
        MutableStateFlow(FavoritesScreenState.Loading)

    fun getFavoriteHymns(): Flow<FavoritesScreenState> {
        updateFavorites()
        return _screenState
    }

    fun toggleFavorite(hymn: HymnModel) {
        toggleFavoriteHymnUseCase.execute(hymn)
        updateFavorites()
    }

    private fun updateFavorites() = viewModelScope.launch {
        val favoriteHymns = getFavoriteHymnsUseCase.execute()
        if (favoriteHymns.isEmpty()) {
            _screenState.update { FavoritesScreenState.Error("No favorite hymns found") }
        } else {
            _screenState.update { FavoritesScreenState.Success(favoriteHymns) }
        }
    }
}

sealed class FavoritesScreenState {
    data object Loading : FavoritesScreenState()
    data class Success(val hymns: List<HymnModel>) : FavoritesScreenState()
    data class Error(val message: String) : FavoritesScreenState()
}
package de.dannyb.imnuri.hymns

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.dannyb.imnuri.domain.usecase.GetAllHymnsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HymnsViewModel @Inject constructor(
    private val getAllHymnsUseCase: GetAllHymnsUseCase
) : ViewModel() {

    fun getAllHymns() = flow {
        val hymns = withContext(Dispatchers.IO) { getAllHymnsUseCase.execute() }
        emit(hymns)
    }

}
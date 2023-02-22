package uz.gita.presentation.viewmodel.impl

import android.database.Cursor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.domain.usecase.CursorRefUseCase
import uz.gita.presentation.viewmodel.PlayListViewModel
import javax.inject.Inject

@HiltViewModel
class PlayListViewModelImpl @Inject constructor(
    private val cursorRefUseCase: CursorRefUseCase
): PlayListViewModel,ViewModel(){

    override val cursorRefFlow = MutableStateFlow<Cursor?>(null)

    init {
        cursorRefUseCase.invoke().onEach {
            cursorRefFlow.emit(it)
        }.launchIn(viewModelScope)
    }
}
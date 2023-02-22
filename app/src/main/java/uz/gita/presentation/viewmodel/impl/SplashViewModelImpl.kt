package uz.gita.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.domain.usecase.GetMusicFromLocalUseCase
import uz.gita.presentation.ui.screen.SplashScreenDirections
import uz.gita.presentation.viewmodel.SplashViewModel
import uz.infinity.memorygamebottcamp4.navigation.NavigationHandler
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val navigationHandler: NavigationHandler,
    private val getMusicFromLocalUseCase: GetMusicFromLocalUseCase
): SplashViewModel,ViewModel(){


    override fun getMusicFromLocal() {
        getMusicFromLocalUseCase.invoke().onEach {
            delay(1500)
            navigationHandler.navigationTo(SplashScreenDirections.actionSplashScreenToPlayListScreen())
        }.launchIn(viewModelScope)
    }

}
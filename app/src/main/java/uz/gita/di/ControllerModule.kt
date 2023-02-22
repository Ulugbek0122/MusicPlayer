package uz.gita.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.presentation.controller.MusicController
import uz.gita.presentation.controller.impl.MusicControllerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ControllerModule {

    @Binds
    fun bindMusicController(impl:MusicControllerImpl):MusicController
}
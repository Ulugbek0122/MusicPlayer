package uz.gita.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import uz.gita.domain.usecase.CursorRefUseCase
import uz.gita.domain.usecase.GetMusicFromLocalUseCase
import uz.gita.domain.usecase.impl.CursorRefUseCaseImpl
import uz.gita.domain.usecase.impl.GetMusicFromLocalUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsCursorRefUseCase(impl:CursorRefUseCaseImpl):CursorRefUseCase

    @Binds
    fun bindsGetMusicFromLocalUseCase(impl:GetMusicFromLocalUseCaseImpl):GetMusicFromLocalUseCase
}
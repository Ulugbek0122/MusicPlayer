package uz.gita.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.domain.repository.AppRepository
import uz.gita.domain.repository.impl.AppRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsAppRepository(impl:AppRepositoryImpl):AppRepository
}
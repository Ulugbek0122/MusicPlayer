package uz.gita.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.domain.repository.AppRepository
import uz.gita.domain.usecase.GetMusicFromLocalUseCase
import javax.inject.Inject

class GetMusicFromLocalUseCaseImpl @Inject constructor(
    private val appRepository: AppRepository
):GetMusicFromLocalUseCase{
    override fun invoke(): Flow<Unit> = flow {
        appRepository.getMusicFromLocal()
        emit(Unit)
    }


}
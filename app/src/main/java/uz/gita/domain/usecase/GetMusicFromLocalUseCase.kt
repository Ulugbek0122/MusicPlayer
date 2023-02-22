package uz.gita.domain.usecase

import kotlinx.coroutines.flow.Flow


interface GetMusicFromLocalUseCase {
    fun invoke():Flow<Unit>
}
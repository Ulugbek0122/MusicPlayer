package uz.gita.domain.usecase.impl

import kotlinx.coroutines.flow.flow
import uz.gita.domain.repository.AppRepository
import uz.gita.domain.usecase.CursorRefUseCase
import javax.inject.Inject

class CursorRefUseCaseImpl @Inject constructor(
    private val appRepository: AppRepository
): CursorRefUseCase {
    override fun invoke() = flow {
        emit(appRepository.getCursor())
    }
}
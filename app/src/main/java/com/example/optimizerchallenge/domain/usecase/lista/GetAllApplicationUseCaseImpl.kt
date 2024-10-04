package com.example.optimizerchallenge.domain.usecase.lista

import com.example.optimizerchallenge.domain.entity.ApplicationDomData
import com.example.optimizerchallenge.domain.repository.ApplicationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllApplicationUseCaseImpl @Inject constructor(private val repository: ApplicationRepository) : GetAllApplicationUseCase {
    override suspend fun invoke() : Flow<List<ApplicationDomData>> {
        return repository.getApplications()
    }
}
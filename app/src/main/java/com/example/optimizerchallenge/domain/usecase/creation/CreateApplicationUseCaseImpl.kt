package com.example.optimizerchallenge.domain.usecase.creation

import com.example.optimizerchallenge.domain.entity.ApplicationDomData
import com.example.optimizerchallenge.domain.repository.ApplicationRepository
import javax.inject.Inject

class CreateApplicationUseCaseImpl @Inject constructor(private val repository: ApplicationRepository) : CreateApplicationUseCase {
    override suspend fun invoke(app: ApplicationDomData): Long {
        return repository.insertApplication(app)
    }
}
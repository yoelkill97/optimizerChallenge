package com.example.optimizerchallenge.domain.usecase.recommendations

import com.example.optimizerchallenge.domain.repository.ApplicationRepository
import javax.inject.Inject

class GetRecommendationsUseCaseImpl  @Inject constructor(private val repository: ApplicationRepository) : GetRecommendationsUseCase {
    override suspend fun invoke(): String {
        return repository.getRecommendations()
    }
}
package com.example.optimizerchallenge.domain.usecase.recommendations

interface GetRecommendationsUseCase {
    suspend fun invoke(): String
}
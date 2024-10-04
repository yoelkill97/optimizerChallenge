package com.example.optimizerchallenge.domain.usecase.creation

import com.example.optimizerchallenge.domain.entity.ApplicationDomData

interface CreateApplicationUseCase {
    suspend fun invoke(app: ApplicationDomData) : Long
}
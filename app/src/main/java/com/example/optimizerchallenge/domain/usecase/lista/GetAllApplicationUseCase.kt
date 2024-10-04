package com.example.optimizerchallenge.domain.usecase.lista

import com.example.optimizerchallenge.domain.entity.ApplicationDomData
import kotlinx.coroutines.flow.Flow


interface GetAllApplicationUseCase {
    suspend fun invoke(): Flow<List<ApplicationDomData>>
}
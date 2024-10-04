package com.example.optimizerchallenge.domain.repository

import com.example.optimizerchallenge.domain.entity.ApplicationDomData
import kotlinx.coroutines.flow.Flow

interface ApplicationRepository {
    suspend fun getApplications(): Flow<List<ApplicationDomData>>
    suspend fun insertApplication(app: ApplicationDomData) : Long
    suspend fun getRecommendations(): String
}
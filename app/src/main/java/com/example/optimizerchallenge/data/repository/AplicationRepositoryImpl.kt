package com.example.optimizerchallenge.data.repository

import android.util.Log
import com.example.optimizerchallenge.data.room.dao.ApplicationDao
import com.example.optimizerchallenge.data.source.mapper.ApplicationRoomMapperImpl
import com.example.optimizerchallenge.data.util.Optimizer
import com.example.optimizerchallenge.domain.entity.ApplicationDomData
import com.example.optimizerchallenge.domain.repository.ApplicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
    private val mapperImpl: ApplicationRoomMapperImpl,
    private val applicationDao: ApplicationDao
) : ApplicationRepository {
    private fun getAllApplications() = applicationDao.getAllApplications()
    override suspend fun getApplications(): Flow<List<ApplicationDomData>> {
        return applicationDao.getAllApplications()
            .map { appEntities ->
                appEntities.map { entity -> mapperImpl.applicationRoomToData(entity) }
            }
    }
    override suspend fun insertApplication(app: ApplicationDomData):Long {
        Log.d("apps1", app.toString())
      return  applicationDao.insertApplication(mapperImpl.applicationDataToRoom(app))
    }

    override suspend fun getRecommendations(): String {
        val optimizer = Optimizer()
        val apps = applicationDao.getAllApplications().first()
        Log.d("apps", apps.toString())
        return optimizer.generateRecommendations(apps)
    }
}
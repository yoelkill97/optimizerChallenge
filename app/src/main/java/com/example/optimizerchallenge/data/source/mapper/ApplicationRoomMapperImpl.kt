package com.example.optimizerchallenge.data.source.mapper

import com.example.optimizerchallenge.data.room.entity.ApplicationEntity
import com.example.optimizerchallenge.domain.entity.ApplicationDomData
import javax.inject.Inject

class ApplicationRoomMapperImpl @Inject constructor() : ApplicationRoomMapper {
    override suspend fun applicationDataToRoom(data: ApplicationDomData): ApplicationEntity {
        return ApplicationEntity(
            name = data.name,
            version =  data.version,
            frequencyUsage = data.frequencyUsage,
            lastUsedDays = data.lastUsedDays,
            cpuUsage = data.cpuUsage,
            ramUsage = data.ramUsage,
            isSupported = data.isSupported
        )
    }

    override suspend fun applicationRoomToData(data: ApplicationEntity): ApplicationDomData {
        return ApplicationDomData(
            data.name,
            data.version,
            data.frequencyUsage,
            data.lastUsedDays,
            data.cpuUsage,
            data.ramUsage,
            data.isSupported
        )
    }
}
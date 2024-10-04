package com.example.optimizerchallenge.data.source.mapper

import com.example.optimizerchallenge.data.room.entity.ApplicationEntity
import com.example.optimizerchallenge.domain.entity.ApplicationDomData

interface ApplicationRoomMapper {
    suspend fun applicationDataToRoom(data : ApplicationDomData) : ApplicationEntity
    suspend fun applicationRoomToData(data : ApplicationEntity) : ApplicationDomData
}
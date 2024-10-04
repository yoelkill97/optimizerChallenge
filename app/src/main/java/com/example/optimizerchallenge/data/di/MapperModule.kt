package com.example.optimizerchallenge.data.di

import com.example.optimizerchallenge.data.source.mapper.ApplicationRoomMapper
import com.example.optimizerchallenge.data.source.mapper.ApplicationRoomMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    abstract fun bindMapperRoomMapper(mapperImpl: ApplicationRoomMapperImpl): ApplicationRoomMapper
}
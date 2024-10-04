package com.example.optimizerchallenge.data.di

import android.content.Context
import androidx.room.Room
import com.example.optimizerchallenge.data.room.AppDatabase
import com.example.optimizerchallenge.data.room.dao.ApplicationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Singleton
    @Provides
    fun providerRoomDatabase(@ApplicationContext context: Context) =
         Room.databaseBuilder(
             context,
            AppDatabase::class.java,
            "application_database"
        ).build()

    @Provides
    fun providerApplicationDao(appDatabase: AppDatabase) : ApplicationDao
    = appDatabase.applicationDao()
}
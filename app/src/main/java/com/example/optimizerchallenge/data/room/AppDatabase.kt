package com.example.optimizerchallenge.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.optimizerchallenge.data.room.entity.ApplicationEntity
import com.example.optimizerchallenge.data.room.dao.ApplicationDao

@Database(entities = [ApplicationEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun applicationDao(): ApplicationDao
}
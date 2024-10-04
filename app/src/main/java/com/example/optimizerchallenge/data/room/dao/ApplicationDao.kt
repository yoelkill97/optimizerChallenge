package com.example.optimizerchallenge.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.optimizerchallenge.data.room.entity.ApplicationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao {
    @Query("SELECT * FROM applications")
    fun getAllApplications():Flow<List<ApplicationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApplication(app: ApplicationEntity) : Long

    @Delete
    fun deleteApplication(app: ApplicationEntity)
}

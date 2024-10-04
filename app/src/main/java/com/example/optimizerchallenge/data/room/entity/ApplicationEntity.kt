package com.example.optimizerchallenge.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entidad de la base de datos que representa una aplicación
@Entity(tableName = "applications")
data class ApplicationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val version: String,
    val frequencyUsage: Int, // Frecuencia de uso (veces al mes)
    val lastUsedDays: Int, // Última fecha de uso en días
    val cpuUsage: Int, // Consumo de CPU en porcentaje
    val ramUsage: Int, // Consumo de RAM en porcentaje
    val isSupported: Boolean // Si está soportada o no
)

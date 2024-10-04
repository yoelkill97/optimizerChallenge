package com.example.optimizerchallenge.domain.entity

data class ApplicationDomData(
    val name: String,
    val version: String,
    val frequencyUsage: Int, // Frecuencia de uso (veces al mes)
    val lastUsedDays: Int, // Última fecha de uso en días
    val cpuUsage: Int, // Consumo de CPU en porcentaje
    val ramUsage: Int, // Consumo de RAM en porcentaje
    val isSupported: Boolean // Si está soportada o no
)

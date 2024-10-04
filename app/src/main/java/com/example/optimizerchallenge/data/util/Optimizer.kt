package com.example.optimizerchallenge.data.util

import com.example.optimizerchallenge.data.room.entity.ApplicationEntity

class Optimizer {


    fun getObsoleteApplications(apps: List<ApplicationEntity>): List<ApplicationEntity> {
        return apps.filter { it.frequencyUsage < 5 && it.lastUsedDays > 180 }
    }


    fun getUnderutilizedResources(apps: List<ApplicationEntity>): List<ApplicationEntity> {
        return apps.filter { it.cpuUsage < 20 && it.ramUsage < 30 }
    }


    fun getRedundantApplications(apps: List<ApplicationEntity>): List<ApplicationEntity> {
        return apps.groupBy { it.name }.filter { it.value.size > 1 }.flatMap { it.value }
    }


    fun generateRecommendations(apps: List<ApplicationEntity>): String {

        val obsolete = getObsoleteApplications(apps)
        val underutilized = getUnderutilizedResources(apps)
        val redundant = getRedundantApplications(apps)


        val report = StringBuilder()
        report.append("Análisis de Aplicaciones\n")
        report.append("Aplicaciones analizadas: ${apps.size}\n")
        report.append("Nombres de las aplicaciones analizadas: ${apps.joinToString { it.name }}\n")


        if (obsolete.isNotEmpty()) {
            report.append("\nAplicaciones Obsoletas:\n")
            obsolete.forEach { app ->
                report.append("- ${app.name}: Último uso hace ${app.lastUsedDays} días, frecuencia de uso: ${app.frequencyUsage} veces/mes.\n")
            }
        } else {
            report.append("\nNo se encontraron aplicaciones obsoletas.\n")
        }

        if (underutilized.isNotEmpty()) {
            report.append("\nRecursos Subutilizados:\n")
            underutilized.forEach { app ->
                report.append("- ${app.name}: Consumo de CPU: ${app.cpuUsage}%, Consumo de RAM: ${app.ramUsage}%.\n")
            }
        } else {
            report.append("\nNo se encontraron aplicaciones con recursos subutilizados.\n")
        }

        if (redundant.isNotEmpty()) {
            report.append("\nAplicaciones Redundantes:\n")
            val groupedRedundancies = redundant.groupBy { it.name }
            groupedRedundancies.forEach { (name, apps) ->
                report.append("- Aplicación: $name, instancias redundantes: ${apps.size}\n")
            }
        } else {
            report.append("\nNo se encontraron aplicaciones redundantes.\n")
        }

        // Verificar si todo está en orden
        if (obsolete.isEmpty() && underutilized.isEmpty() && redundant.isEmpty()) {
            report.append("\nTodo está en orden. No se encontraron problemas con las aplicaciones analizadas.\n")
        } else {
            report.append("\nRecomendación general: Revisa las aplicaciones obsoletas, subutilizadas o redundantes para optimizar recursos.\n")
        }

        // Devolver el reporte final
        return report.toString()
    }
}

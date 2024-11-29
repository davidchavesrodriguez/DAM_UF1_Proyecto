package com.example.gblorchos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "resultados")
data class Resultado(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val equipo1Id: Long,
    val equipo2Id: Long,
    val marcadorEquipo1: Int,
    val marcadorEquipo2: Int,
)

package com.example.gblorchos.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ResultadoConEquipos(
    val id: Long,
    val equipo1Id: Int,
    val equipo2Id: Int,
    val marcadorEquipo1: Int,
    val marcadorEquipo2: Int,
    val equipo1Nombre: String,
    val equipo2Nombre: String,
    val equipo1Imagen: String,
    val equipo2Imagen: String
)

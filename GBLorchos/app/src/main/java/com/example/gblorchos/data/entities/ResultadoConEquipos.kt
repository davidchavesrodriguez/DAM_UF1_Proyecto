package com.example.gblorchos.data.entities

data class ResultadoConEquipos(
    val id: Long,
    val equipo1Id: Int,
    val equipo2Id: Int,
    val marcadorEquipo1: Int,
    val marcadorEquipo2: Int,
    val equipo1Nombre: String,
    val equipo2Nombre: String
)

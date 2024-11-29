package com.example.gblorchos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "equipo")
data class Equipo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val ubicacion: String,
    val imagen: String? = null
)
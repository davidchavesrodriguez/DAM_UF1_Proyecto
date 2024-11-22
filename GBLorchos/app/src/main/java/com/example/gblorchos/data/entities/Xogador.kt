package com.example.gblorchos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "xogadores")
data class Xogador(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val posicion: String,
    val fechaNacemento: Long,
    val puntos: Int
)
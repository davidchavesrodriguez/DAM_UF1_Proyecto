package com.example.gblorchos.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "xogadores",
    foreignKeys = [ForeignKey(
        entity = Equipo::class,
        parentColumns = ["id"],
        childColumns = ["equipoId"],
        onDelete = ForeignKey.CASCADE)]
)
data class Xogador(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val posicion: String,
    val fechaNacemento: Long,
    val puntos: Int,
    val equipoId: Long
)

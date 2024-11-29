package com.example.gblorchos.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gblorchos.data.entities.Equipo
import com.example.gblorchos.data.entities.Xogador

@Dao
interface EquipoDao {

    @Query("SELECT * FROM equipo WHERE id = :id")
    fun getEquipoById(id: Long): Equipo?

    @Insert
    suspend fun insert(equipo: Equipo)

    @Query("SELECT * FROM equipo")
    suspend fun getAllEquipos(): List<Equipo>
}
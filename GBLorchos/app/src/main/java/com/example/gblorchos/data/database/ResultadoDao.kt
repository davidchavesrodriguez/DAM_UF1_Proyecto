package com.example.gblorchos.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.gblorchos.data.entities.Resultado
import com.example.gblorchos.data.entities.ResultadoConEquipos

@Dao
interface ResultadoDao {

    @Insert
    suspend fun insertResultado(resultado: Resultado)

    @Insert
    suspend fun insertResultados(resultados: List<Resultado>)

    @Query(
        """
    SELECT r.*, e1.nombre AS equipo1Nombre, e2.nombre AS equipo2Nombre, 
    e1.imagen AS equipo1Imagen, e2.imagen AS equipo2Imagen
    FROM resultados r
    JOIN equipo e1 ON r.equipo1Id = e1.id
    JOIN equipo e2 ON r.equipo2Id = e2.id
"""
    )
    suspend fun getAllResultadosConEquipos(): List<ResultadoConEquipos>
}
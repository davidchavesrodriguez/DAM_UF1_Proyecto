package com.example.gblorchos.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gblorchos.data.entities.Resultado
import com.example.gblorchos.data.entities.ResultadoConEquipos

@Dao
interface ResultadoDao {

    @Insert
    suspend fun insertResultado(resultado: Resultado)

    @Insert
    suspend fun insertResultados(resultados: List<Resultado>)

    // Consulta JOIN para obtener los resultados con los nombres de los equipos
    @Query("""
        SELECT r.*, e1.nombre AS equipo1Nombre, e2.nombre AS equipo2Nombre
        FROM resultados r
        JOIN equipo e1 ON r.equipo1Id = e1.id
        JOIN equipo e2 ON r.equipo2Id = e2.id
    """)
    suspend fun getAllResultadosConNombres(): List<ResultadoConEquipos>
}

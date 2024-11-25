package com.example.gblorchos.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gblorchos.data.entities.Xogador

@Dao
interface XogadorDao {

    @Insert
    suspend fun insertXogador(xogador: Xogador)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllXogadores(xogadores: List<Xogador>)

    @Query("SELECT * FROM xogadores")
    suspend fun getAllXogadores(): List<Xogador>

    @Query("SELECT * FROM xogadores WHERE id= :id")
    suspend fun getXogadorById(id: Int): Xogador?

    @Query("DELETE FROM xogadores")
    suspend fun deleteAllXogadores()
}
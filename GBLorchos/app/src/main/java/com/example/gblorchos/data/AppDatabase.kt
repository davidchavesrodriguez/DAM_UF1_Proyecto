package com.example.gblorchos.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gblorchos.data.dao.XogadorDao
import com.example.gblorchos.data.entities.Xogador

@Database(entities = [Xogador::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun xogadorDao(): XogadorDao
}
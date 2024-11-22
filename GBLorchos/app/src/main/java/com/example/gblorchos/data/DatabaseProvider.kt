package com.example.gblorchos.data

import android.content.Context
import androidx.room.Room
import com.example.gblorchos.data.dao.XogadorDao

class DatabaseProvider(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "gblorchos-db"
    ).build()

    val xogadorDao: XogadorDao = db.xogadorDao()
}

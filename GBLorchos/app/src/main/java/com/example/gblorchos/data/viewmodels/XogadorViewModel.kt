package com.example.gblorchos.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.gblorchos.data.AppDatabase
import com.example.gblorchos.data.entities.Xogador
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class XogadorViewModel(application: Application) : AndroidViewModel(application) {

    // Usar una instancia estática de AppDatabase, asegurándose de que la base de datos sea compartida
    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(application, AppDatabase::class.java, "gblorchos-db").build()
    }

    // MutableStateFlow para almacenar los xogadores
    private val _xogadores = MutableStateFlow<List<Xogador>>(emptyList())
    val xogadores: StateFlow<List<Xogador>> get() = _xogadores

    // Función para cargar los xogadores desde la base de datos
    fun loadXogadores() {
        viewModelScope.launch {
            try {
                // Usar Dispatchers.IO para operaciones de base de datos
                val xogadoresList = withContext(Dispatchers.IO) {
                    appDatabase.xogadorDao().getAllXogadores()
                }
                _xogadores.value = xogadoresList
            } catch (e: Exception) {
                // Manejo de errores si ocurre un problema con la base de datos
                e.printStackTrace()
                _xogadores.value = emptyList() // En caso de error, asignamos una lista vacía
            }
        }
    }
}

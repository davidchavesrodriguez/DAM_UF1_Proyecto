package com.example.gblorchos.data.viewmodels

import android.app.Application
import android.util.Log
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

    private val appDatabase: AppDatabase by lazy {
        AppDatabase.getDatabase(application.applicationContext)
    }

    private val _xogadores = MutableStateFlow<List<Xogador>>(emptyList())
    val xogadores: StateFlow<List<Xogador>> get() = _xogadores

    fun loadXogadores() {
        viewModelScope.launch {
            try {
                val xogadoresList = withContext(Dispatchers.IO) {
                    appDatabase.xogadorDao().getAllXogadores()
                }
                _xogadores.value = xogadoresList
            } catch (e: Exception) {
                e.printStackTrace()
                _xogadores.value = emptyList()
            }
        }
    }

    // Función para insertar un xogador
    fun insertXogador(xogador: Xogador) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    appDatabase.xogadorDao().insertXogador(xogador)
                }
                loadXogadores()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

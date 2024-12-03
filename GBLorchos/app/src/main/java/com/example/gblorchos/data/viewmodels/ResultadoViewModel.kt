package com.example.gblorchos.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gblorchos.data.AppDatabase
import com.example.gblorchos.data.entities.Resultado
import com.example.gblorchos.data.entities.ResultadoConEquipos
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResultadoViewModel(application: Application) : AndroidViewModel(application) {

    private val appDatabase: AppDatabase by lazy {
        AppDatabase.getDatabase(application.applicationContext)
    }

    private val _resultados = MutableStateFlow<List<ResultadoConEquipos>>(emptyList())
    val resultados: StateFlow<List<ResultadoConEquipos>> get() = _resultados

    fun loadResultados() {
        viewModelScope.launch {
            try {
                val resultadosList = withContext(Dispatchers.IO) {
                    appDatabase.resultadoDao().getAllResultadosConEquipos()
                }

                _resultados.value = resultadosList
            } catch (e: Exception) {
                e.printStackTrace()
                _resultados.value = emptyList()
            }
        }
    }
}

package com.example.gblorchos.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gblorchos.data.entities.Xogador
import com.example.gblorchos.data.viewmodels.XogadorViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlayerDialog(
    onDismiss: () -> Unit,
    onSave: (name: String, position: String, birthDate: String, points: String) -> Unit,
    xogadorViewModel: XogadorViewModel
) {
    var nome by remember { mutableStateOf("") }
    var posicion by remember { mutableStateOf("") }
    var fechaNacemento by remember { mutableStateOf("") }
    var puntos by remember { mutableStateOf("") }

    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }

    val context = LocalContext.current

    val positionList = listOf("Porteiro", "Defensa", "Medio", "Dianteiro")

    // Este efecto se lanza cuando showToast cambia
    LaunchedEffect(showToast) {
        if (showToast) {
            // Mostrar el Toast
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            // Después de mostrar el Toast, restablecer el estado de showToast a false
            showToast = false
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Añadir Jugador") },
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                TextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                TextField(
                    value = posicion,
                    onValueChange = { posicion = it },
                    label = { Text("Posición") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                TextField(
                    value = fechaNacemento,
                    onValueChange = { fechaNacemento = it },
                    label = { Text("Fecha de Nacimiento (yyyy-MM-dd)") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                TextField(
                    value = puntos,
                    onValueChange = { puntos = it },
                    label = { Text("Puntos") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    // Validar los campos
                    if (nome.isBlank() || posicion.isBlank() || fechaNacemento.isBlank() || puntos.isBlank()) {
                        toastMessage = "Por favor, complete todos los campos"
                        showToast = true
                    } else {
                        var validInput = true
                        // Validar fecha con regex para asegurarse de que está en el formato correcto
                        val fechaValida = fechaNacemento.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))
                        if (!fechaValida) {
                            toastMessage = "Formato de fecha inválido"
                            showToast = true
                            validInput = false
                        }

                        // Validar puntos
                        val puntosInt = puntos.toIntOrNull()
                        if (puntosInt == null) {
                            toastMessage = "Formato de puntos inválido"
                            showToast = true
                            validInput = false
                        }

                        // Si hay algún error, no guardar el jugador
                        if (validInput) {
                            try {
                                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                val date = sdf.parse(fechaNacemento)

                                val xogador = Xogador(
                                    nome = nome,
                                    posicion = posicion,
                                    fechaNacemento = date?.time ?: 0L,
                                    puntos = puntosInt ?: 0,
                                    equipoId = 5
                                )

                                // Insertar el jugador en la base de datos
                                xogadorViewModel.insertXogador(xogador)

                                // Ahora que el jugador se ha guardado, llamar a onSave
                                onSave(nome, posicion, fechaNacemento, puntos)

                                // Mostrar un mensaje de éxito después de guardar
                                toastMessage = "Jugador guardado correctamente"
                                showToast = true
                            } catch (e: Exception) {
                                toastMessage = "Error al guardar el jugador"
                                showToast = true
                            }
                        }
                    }
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

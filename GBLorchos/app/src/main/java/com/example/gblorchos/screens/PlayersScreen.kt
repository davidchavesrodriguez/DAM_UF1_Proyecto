package com.example.gblorchos.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.gblorchos.R
import com.example.gblorchos.data.entities.Xogador
import com.example.gblorchos.ui.theme.GBLorchosTheme
import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.draw.clip
import com.example.gblorchos.data.viewmodels.XogadorViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun XogadoresContent(
    xogadores: List<Xogador>,
    modifier: Modifier = Modifier,
    xogadorViewModel: XogadorViewModel
) {
    var showDialog by remember { mutableStateOf(false) }

    var nome by remember { mutableStateOf("") }
    var posicion by remember { mutableStateOf("") }
    var fechaNacemento by remember { mutableStateOf("") }
    var puntos by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.players),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp).align(CenterHorizontally)
            )
            Text(
                text = stringResource(R.string.players_description),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp).align(CenterHorizontally)
            )

            if (xogadores.isEmpty()) {
                Text("Error!!")
            } else {
                xogadores.forEach { xogador ->
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        shape = MaterialTheme.shapes.medium, // Borde redondeado
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp) // Espacio extra entre cards
                            .border(
                                8.dp,
                                getColorByPosition(xogador.posicion),
                                MaterialTheme.shapes.medium
                            ) // Aplicar borde color por posición
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(colorResource(id = R.color.lorchos).copy(alpha = 0.15f))
                                .padding(16.dp)
                        ) {
                            // Dentro del Column donde se muestran los Xogadores

                            Column(modifier = Modifier.fillMaxSize()) {
                                val imageUrl =
                                    "https://thispersondoesnotexist.com?${xogador.nome.hashCode()}"
                                val painter = rememberImagePainter(imageUrl)

                                // Imagen del jugador
                                Image(
                                    painter = painter,
                                    contentDescription = "Foto do xogador",
                                    modifier = Modifier
                                        .size(120.dp)
                                        .align(CenterHorizontally)
                                        .clip(MaterialTheme.shapes.medium) // Imagen redondeada
                                        .border(
                                            4.dp,
                                            getColorByPosition(xogador.posicion),
                                            MaterialTheme.shapes.medium
                                        ) // Borde color según posición
                                )

                                // Nombre del jugador
                                Text(
                                    text = xogador.nome,
                                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                                    modifier = Modifier
                                        .align(CenterHorizontally)
                                        .padding(vertical = 8.dp) // Espacio vertical entre nombre y otros detalles
                                )

                                // Información del jugador organizada en un solo bloque
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp), // Paddings horizontal entre columnas
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = xogador.posicion,
                                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .weight(1f) // Para asegurar que cada texto tenga el mismo espacio
                                            .align(Alignment.CenterVertically),
                                        textDecoration = TextDecoration.Underline
                                    )
                                    Text(
                                        text = stringResource(
                                            R.string.birth_year,
                                            formatDate(xogador.fechaNacemento)
                                        ),
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .weight(1f) // Se distribuye mejor
                                            .align(Alignment.CenterVertically)
                                    )
                                    Text(
                                        text = stringResource(R.string.points, xogador.puntos),
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .weight(1f) // Asegura que ocupe el espacio proporcional
                                            .align(Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Añadir jugador")
        }

        if (showDialog) {
            AddPlayerDialog(
                onDismiss = { showDialog = false },
                onSave = { name, position, birthDate, points ->
                    val newPlayer = Xogador(
                        nome = name,
                        posicion = position,
                        fechaNacemento = birthDate.toLong(),
                        puntos = points.toInt(),
                        equipoId = 1
                    )
                    xogadorViewModel.insertXogador(newPlayer)
                    showDialog = false
                },
                xogadorViewModel = xogadorViewModel
            )
        }
    }
}

// Función que devuelve un color según la posición del jugador
@Composable
fun getColorByPosition(posicion: String): Color {
    return when (posicion) {
        "Porteiro" -> colorResource(id = R.color.porteiro)
        "Defensa" -> colorResource(id = R.color.defensa)
        "Medio" -> colorResource(id = R.color.medio)
        "Dianteiro" -> colorResource(id = R.color.dianteiro)
        else -> Color.Gray
    }
}

// Función para convertir un valor Long a una fecha formateada
fun formatDate(dateLong: Long): String {
    val dateFormat = SimpleDateFormat(
        "dd/MM/yyyy",
        Locale.getDefault()
    )
    return dateFormat.format(Date(dateLong))
}

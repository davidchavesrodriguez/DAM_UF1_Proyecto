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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.gblorchos.data.viewmodels.XogadorViewModel
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding


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

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.estrada),
                contentDescription = stringResource(R.string.tournament_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.players),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black)
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .align(CenterHorizontally)
            )

            // Lista de jugadores o mensaje de error
            if (xogadores.isEmpty()) {
                Text(
                    text = "Error!!",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(16.dp)
                )
            } else {
                xogadores.forEach { xogador ->
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .border(
                                8.dp,
                                getColorByPosition(xogador.posicion),
                                MaterialTheme.shapes.medium
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(color = colorResource(id = R.color.lorchos))
                            ) {
                                // TODO: Acción al hacer clic en el jugador
                            },
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(getRandomGradient())
                                .padding(16.dp)
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                val imageUrl =
                                    "https://thispersondoesnotexist.com?${xogador.nome.hashCode()}"
                                val painter = rememberImagePainter(imageUrl)

                                // Imagen del jugador
                                Image(
                                    painter = painter,
                                    contentDescription = xogador.nome,
                                    modifier = Modifier
                                        .size(120.dp)
                                        .align(CenterHorizontally)
                                        .clip(MaterialTheme.shapes.medium)
                                        .border(
                                            4.dp,
                                            getColorByPosition(xogador.posicion),
                                            MaterialTheme.shapes.medium
                                        )
                                )

                                // Nombre del jugador
                                Text(
                                    text = xogador.nome,
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = colorResource(id = R.color.black)
                                    ),
                                    modifier = Modifier
                                        .align(CenterHorizontally)
                                        .padding(vertical = 8.dp)
                                )

                                // Fila con la información adicional del jugador
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .background(color = colorResource(id = R.color.white))
                                        .align(CenterHorizontally)
                                ) {
                                    Text(
                                        text = xogador.posicion,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = getColorByPosition(xogador.posicion)
                                        ),
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .weight(1f)
                                            .align(Alignment.CenterVertically),
                                        textDecoration = TextDecoration.Underline
                                    )

                                    Text(
                                        text = stringResource(
                                            R.string.birth_year,
                                            formatDate(xogador.fechaNacemento)
                                        ),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            getColorByPosition(xogador.posicion)
                                        ),
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .weight(1f)
                                            .align(Alignment.CenterVertically)
                                    )

                                    Text(
                                        text = stringResource(R.string.points, xogador.puntos),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = getColorByPosition(xogador.posicion)
                                        ),
                                        modifier = Modifier
                                            .weight(1f)
                                            .align(Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // FloatingActionButton flotante en la esquina inferior derecha
        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Añadir jugador")
        }
    }

    // Diálogo para añadir jugador
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
                showDialog = false
                xogadorViewModel.insertXogador(newPlayer)
            },
            xogadorViewModel = xogadorViewModel
        )
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

// Color de gradiente aleatorio
@Composable
fun getRandomGradient(): Brush {
    val colors = listOf(
        colorResource(id = R.color.lorchos).copy(alpha = 0.1f),
        colorResource(id = R.color.lorchos).copy(alpha = 0.2f),
        colorResource(id = R.color.lorchos).copy(alpha = 0.3f),
        colorResource(id = R.color.lorchos).copy(alpha = 0.4f),
        colorResource(id = R.color.lorchos).copy(alpha = 0.5f),
        colorResource(id = R.color.lorchos).copy(alpha = 0.6f),
        colorResource(id = R.color.lorchos).copy(alpha = 0.7f),
    )
    val shuffledColors = colors.shuffled(Random(System.currentTimeMillis()))

    return Brush.linearGradient(colors = shuffledColors)
}


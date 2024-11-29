package com.example.gblorchos.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gblorchos.R
import com.example.gblorchos.data.entities.Xogador
import com.example.gblorchos.screens.getColorByPosicion
import com.example.gblorchos.ui.theme.GBLorchosTheme

@SuppressLint("SimpleDateFormat")
@Composable
fun XogadoresContent(xogadores: List<Xogador>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if (xogadores.isEmpty()) {
            Text("No hay jugadores disponibles") // Mensaje si la lista está vacía
        } else {
            xogadores.forEach { xogador ->
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = getColorByPosicion(xogador.posicion))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = xogador.nome,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.align(CenterHorizontally)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = xogador.posicion,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                            Text(
                                text = stringResource(id = R.string.birth_year,
                                    xogador.fechaNacemento.let { date ->
                                        java.text.SimpleDateFormat("yyyy").format(date)
                                    }
                                ),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = stringResource(id = R.string.points, xogador.puntos),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}


// Función que devolve un color según a posición do xogador
@Composable
fun getColorByPosicion(posicion: String): Color {
    return when (posicion) {
        "Porteiro" -> colorResource(id = R.color.porteiro)
        "Defensa" -> colorResource(id = R.color.defensa)
        "Medio" -> colorResource(id = R.color.medio)
        "Dianteiro" -> colorResource(id = R.color.dianteiro)
        else -> Color.Gray
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewXogadoresContent() {
    GBLorchosTheme {
        XogadoresContent(
            xogadores = listOf(
                Xogador(
                    nome = "Xogador 1",
                    posicion = "Dianteiro",
                    fechaNacemento = 11111111L,
                    puntos = 10,
                    equipoId = 1
                ),
                Xogador(
                    nome = "Xogador 2",
                    posicion = "Medio",
                    fechaNacemento = 11111111L,
                    puntos = 15,
                    equipoId = 1
                )
            )
        )
    }
}


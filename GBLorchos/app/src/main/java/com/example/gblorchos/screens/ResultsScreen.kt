package com.example.gblorchos.screens

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.gblorchos.R
import com.example.gblorchos.data.entities.Resultado
import com.example.gblorchos.data.entities.ResultadoConEquipos
import com.example.gblorchos.ui.theme.GBLorchosTheme
import java.util.Random

@Composable
fun ResultadosContent(resultados: List<ResultadoConEquipos>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        resultados.forEach { resultado ->
            val escudo1 = resultado.equipo1Imagen 
            val escudo2 = resultado.equipo2Imagen

            ElevatedCard(
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(getRandomGradientResults(resultado))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row() {
                            Image(
                                painter = rememberImagePainter(
                                    data = "android.resource://${LocalContext.current.packageName}/drawable/$escudo1"
                                ),
                                contentDescription = "Escudo ${resultado.equipo1Nombre}",
                                modifier = Modifier.size(125.dp).weight(1f)
                            )
                            Image(
                                painter = rememberImagePainter(
                                    data = "android.resource://${LocalContext.current.packageName}/drawable/$escudo2"
                                ),
                                contentDescription = "Escudo ${resultado.equipo2Nombre}",
                                modifier = Modifier.size(125.dp).weight(1f)
                            )
                        }
                        Text(
                            text = "${resultado.equipo1Nombre} vs ${resultado.equipo2Nombre}",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "${resultado.marcadorEquipo1} - ${resultado.marcadorEquipo2}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun getRandomGradientResults(resultado: ResultadoConEquipos): Brush {
    return if (resultado.marcadorEquipo1 > resultado.marcadorEquipo2) {
        val colors = listOf(
            colorResource(id = com.example.gblorchos.R.color.defensa),
            colorResource(id = com.example.gblorchos.R.color.white),
            colorResource(id = com.example.gblorchos.R.color.white),
            colorResource(id = com.example.gblorchos.R.color.defensa),

            )
        Brush.linearGradient(colors)
    } else {
        val colors = listOf(
            colorResource(id = com.example.gblorchos.R.color.dianteiro),
            colorResource(id = com.example.gblorchos.R.color.white),
            colorResource(id = com.example.gblorchos.R.color.white),
            colorResource(id = com.example.gblorchos.R.color.dianteiro),

            )
        Brush.linearGradient(colors)
    }
}

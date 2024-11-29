package com.example.gblorchos.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gblorchos.data.entities.Resultado
import com.example.gblorchos.data.entities.ResultadoConEquipos
import com.example.gblorchos.ui.theme.GBLorchosTheme

@SuppressLint("SimpleDateFormat")
@Composable
fun ResultadosContent(resultados: List<ResultadoConEquipos>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        resultados.forEach { resultado ->
            ElevatedCard(
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // TODO: Añardir escudo
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


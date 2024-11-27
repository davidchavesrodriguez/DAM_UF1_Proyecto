package com.example.gblorchos.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gblorchos.ui.theme.GBLorchosTheme

@Composable
fun ResultadosContent() {
    Text(
        text = "Resultados: Aquí va el texto que explica los resultados...",
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewResultadosContent() {
    GBLorchosTheme {
        ResultadosContent()
    }
}
package com.example.gblorchos.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gblorchos.ui.theme.GBLorchosTheme

@Composable
fun TendaContent() {
    Text(
        text = "Tenda: Aquí va el texto que explica la tienda...",
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTendaContent() {
    GBLorchosTheme {
        TendaContent()
    }
}
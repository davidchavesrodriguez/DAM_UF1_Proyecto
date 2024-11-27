package com.example.gblorchos.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gblorchos.R
import com.example.gblorchos.ui.theme.GBLorchosTheme

@Composable
fun MainContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val escudo: Painter =
            painterResource(id = R.drawable.escudo)
        val estrada: Painter =
            painterResource(id = R.drawable.estrada)
        val curso: Painter =
            painterResource(id = R.drawable.curso)
        val gladiador: Painter =
            painterResource(id = R.drawable.gladiador)
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier
                .padding(16.dp)
                .align(CenterHorizontally),
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = stringResource(R.string.team_subtitle),
            modifier = Modifier
                .padding(16.dp)
                .align(CenterHorizontally),
            style = MaterialTheme.typography.headlineMedium
        )
        Image(
            painter = escudo,
            contentDescription = stringResource(R.string.shield_description),
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.team_description),
                modifier = Modifier
                    .padding(16.dp)
                    .align(CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.estrada),
                    contentDescription = stringResource(R.string.tournament_description),
                    modifier = Modifier
                        .size(190.dp)
                        .padding(8.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.curso),
                    contentDescription = stringResource(R.string.course_description),
                    modifier = Modifier
                        .size(190.dp)
                        .padding(8.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.gladiador),
                contentDescription = stringResource(R.string.gladiator_description),
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
                    .align(CenterHorizontally)
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    GBLorchosTheme {
        MainContent()
    }
}
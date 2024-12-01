package com.example.gblorchos.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gblorchos.R
import com.example.gblorchos.ui.theme.GBLorchosTheme

@Composable
fun MainContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.estrada),
                contentDescription = stringResource(R.string.tournament_description),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(vertical = 16.dp)
        )
        Divider(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(
            text = stringResource(R.string.team_description),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.curso),
                contentDescription = stringResource(R.string.course_description),
                modifier = Modifier.size(100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.gladiador),
                contentDescription = stringResource(R.string.gladiator_description),
                modifier = Modifier.size(100.dp)
            )
        }
        FilledTonalButton(
            onClick = { navController.navigate("xogadores") },
            modifier = Modifier.align(CenterHorizontally),
            colors= ButtonDefaults.filledTonalButtonColors(
                containerColor= colorResource(id = R.color.lorchos),
                contentColor = colorResource(id= R.color.white)
            )
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    GBLorchosTheme {
        val fakeNavController = rememberNavController()
        MainContent(navController = fakeNavController)
    }
}

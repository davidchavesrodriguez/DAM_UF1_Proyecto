package com.example.gblorchos.screens

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.webkit.WebView
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gblorchos.R
import com.example.gblorchos.ui.theme.GBLorchosTheme
import androidx.compose.ui.platform.LocalContext


@Composable
fun MainContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.estrada),
                contentDescription = stringResource(R.string.tournament_description),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = stringResource(R.string.team_description),
            style = MaterialTheme.typography.headlineSmall,
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
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.lorchos).copy(alpha = 0.5f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.curso),
                        contentDescription = stringResource(R.string.course_description),
                        modifier = Modifier.size(100.dp)
                            .align(CenterHorizontally)
                    )
                    Text(
                        text = stringResource(R.string.course_description),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                }
            }

            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.lorchos).copy(alpha = 0.5f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gladiador),
                        contentDescription = stringResource(R.string.gladiator_description),
                        modifier = Modifier.size(100.dp)
                            .align(CenterHorizontally)
                    )
                    Text(
                        text = stringResource(R.string.gladiator_description),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                }
            }
        }

        Divider(
            color = colorResource(id = R.color.lorchos),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = stringResource(R.string.foundation_description),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(16.dp)
                .align(CenterHorizontally)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.lorchos).copy(alpha = 0.5f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.escudo),
                        contentDescription = stringResource(R.string.shield_description),
                        modifier = Modifier.size(100.dp)
                            .weight(1f)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.amigos),
                        contentDescription = stringResource(R.string.amigos_description),
                        modifier = Modifier.size(100.dp)
                            .weight(1f)
                    )
                }
            }

        }


        Divider(
            color = colorResource(id = R.color.lorchos),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = stringResource(R.string.next),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(16.dp)
                .align(CenterHorizontally)
        )

        VideoPlayer(context = LocalContext.current)

        FilledTonalButton(
            onClick = { navController.navigate("xogadores") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(CenterHorizontally),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = colorResource(id = R.color.lorchos),
                contentColor = colorResource(id = R.color.white)
            )
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    GBLorchosTheme {
        val fakeNavController = rememberNavController()
        MainContent(navController = fakeNavController)
    }
}

@Composable
fun VideoPlayer(context: Context) {
    AndroidView(
        factory = { ctx ->
            VideoView(ctx).apply {
                // Aquí aseguramos que el video esté en la carpeta raw
                val uri = Uri.parse("android.resource://${context.packageName}/raw/video")
                setVideoURI(uri)

                // Añadir un Listener para detectar la finalización del video
                setOnCompletionListener {
                    // Aquí puedes realizar acciones cuando termine el video (como reiniciarlo)
                    start()  // Reinicia el video, si lo deseas
                }

                // Comienza a reproducir el video
                start()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

package com.example.gblorchos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gblorchos.data.entities.Xogador
import com.example.gblorchos.data.viewmodels.XogadorViewModel
import com.example.gblorchos.ui.theme.GBLorchosTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GBLorchosTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Obtener el ViewModel de Xogador
    val xogadorViewModel: XogadorViewModel = viewModel()

    // Llamar a loadXogadores para cargar los xogadores desde la base de datos
    LaunchedEffect(Unit) {
        xogadorViewModel.loadXogadores()
    }

    // Usar collectAsState para observar el StateFlow
    val xogadores by xogadorViewModel.xogadores.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        Scaffold(
            topBar = { AppTopBar(onMenuClick = { scope.launch { drawerState.open() } }) },
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController, startDestination = "principal") {
                        composable("principal") { PrincipalContent() }
                        composable("xogadores") { XogadoresContent(xogadores = xogadores) }
                        // Define other destinations (eventos, resultados, tenda)
                    }
                }
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(onMenuClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "GB Lorchos",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
            }
        }
    )
}

@Composable
fun DrawerContent(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { navController.navigate("principal") }) {
            Text("Principal")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("xogadores") }) {
            Text("Xogadores")
        }
        // Add buttons for eventos, resultados, tenda
    }
}

@Composable
fun PrincipalContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        val image: Painter =
            painterResource(id = R.drawable.escudo) // Ensure the image is in /res/drawable
        Image(
            painter = image,
            contentDescription = "Historia de Lorchos",
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Historia de Lorchos: Aquí va el texto que explica la historia...",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun XogadoresContent(xogadores: List<Xogador>, modifier: Modifier = Modifier) {
    // Mostrar los xogadores recuperados
    val xogadoresText = xogadores.joinToString(separator = "\n") {
        "${it.nome}, ${it.posicion}, Ano: ${
            it.fechaNacemento?.let { date ->
                java.text.SimpleDateFormat(
                    "yyyy"
                ).format(date)
            }
        }, Puntos: ${it.puntos}"
    }

    Text(
        text = "Xogadores:\n$xogadoresText",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GBLorchosTheme {
        MainScreen()
    }
}

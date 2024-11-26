package com.example.gblorchos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
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

    // Obter viewmodel de Xogador
    val xogadorViewModel: XogadorViewModel = viewModel()

    // Chamar a loadXogadores() para cargalos dende a base de datos
    LaunchedEffect(Unit) {
        xogadorViewModel.loadXogadores()
    }

    // Usar collectAsState para obter os xogadores
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
                        composable("eventos") { EventosContent() }
                        composable("resultados") { ResultadosContent() }
                        composable("tenda") { TendaContent() }
                    }
                }
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(onMenuClick: () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = "GB Lorchos",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.lorchos)), // Aplica el color de fondo al texto
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menú",
                    modifier = Modifier.background(colorResource(id = R.color.lorchos)) // Aplica el color de fondo al icono
                )
            }
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.escudo),
                contentDescription = "Escudo",
                modifier = Modifier
                    .size(56.dp)
                    .padding(8.dp)
            )
        },
        modifier = modifier
            .background(colorResource(id = R.color.lorchos)) // Aplica el color de fondo al TopAppBar completo
            .padding(16.dp) // Agrega padding si es necesario
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
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("eventos") }) {
            Text("Eventos")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("resultados") }) {
            Text("Resultados")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("tenda") }) {
            Text("Tenda")
        }
    }
}

@Composable
fun PrincipalContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        val image: Painter =
            painterResource(id = R.drawable.escudo)
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

@Composable
fun EventosContent() {
    Text(
        text = "Eventos: Aquí va el texto que explica los eventos...",
    )
}

@Composable
fun ResultadosContent() {
    Text(
        text = "Resultados: Aquí va el texto que explica los resultados...",
    )
}

@Composable
fun TendaContent() {
    Text(
        text = "Tenda: Aquí va el texto que explica la tienda...",
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GBLorchosTheme {
        MainScreen()
    }
}

package com.example.gblorchos

import android.annotation.SuppressLint
import android.icu.text.CaseMap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gblorchos.data.entities.Xogador
import com.example.gblorchos.data.viewmodels.XogadorViewModel
import com.example.gblorchos.ui.theme.GBLorchosTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.TableInfo
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
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menú",
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
            .background(colorResource(id = R.color.lorchos))
            .padding(16.dp)
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
            text = "GB Lorchos",
            modifier = Modifier.padding(16.dp).align(CenterHorizontally),
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Equipo gaélico do Barbanza",
            modifier = Modifier.padding(16.dp).align(CenterHorizontally),
            style = MaterialTheme.typography.headlineMedium
        )
        Image(
            painter = escudo,
            contentDescription = "Escudo de GB Lorchos",
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Fundado no 2023, e para a historia!",
                modifier = Modifier.padding(16.dp).align(CenterHorizontally),
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
                    contentDescription = "Torneo da Estrada",
                    modifier = Modifier
                        .size(190.dp)
                        .padding(8.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.curso),
                    contentDescription = "Lorchocurso",
                    modifier = Modifier
                        .size(190.dp)
                        .padding(8.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.gladiador),
                contentDescription = "Lorcho Gladiador",
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp).align(CenterHorizontally)
            )

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

@SuppressLint("SimpleDateFormat")
@Composable
fun XogadoresContent(xogadores: List<Xogador>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
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
                            text = "Nacemento: ${
                                xogador.fechaNacemento?.let { date ->
                                    java.text.SimpleDateFormat("yyyy").format(date)
                                }
                            }",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Puntos: ${xogador.puntos}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                }
            }
        }
    }
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
fun PreviewPrincipalContent() {
    GBLorchosTheme {
        PrincipalContent()
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
                    puntos = 10
                ),
                Xogador(
                    nome = "Xogador 2",
                    posicion = "Medio",
                    fechaNacemento = 11111111L,
                    puntos = 15
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEventosContent() {
    GBLorchosTheme {
        EventosContent()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewResultadosContent() {
    GBLorchosTheme {
        ResultadosContent()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTendaContent() {
    GBLorchosTheme {
        TendaContent()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDrawerContent() {
    GBLorchosTheme {
        DrawerContent(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppTopBar() {
    GBLorchosTheme {
        AppTopBar(onMenuClick = {})
    }
}

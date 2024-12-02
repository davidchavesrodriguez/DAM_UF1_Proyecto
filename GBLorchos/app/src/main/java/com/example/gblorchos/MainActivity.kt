package com.example.gblorchos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gblorchos.data.viewmodels.XogadorViewModel
import com.example.gblorchos.ui.theme.GBLorchosTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gblorchos.data.entities.Xogador
import com.example.gblorchos.data.viewmodels.ResultadoViewModel
import com.example.gblorchos.screens.AddPlayerDialog
import com.example.gblorchos.screens.EventosContent
import com.example.gblorchos.screens.MainContent
import com.example.gblorchos.screens.ResultadosContent
import com.example.gblorchos.screens.TendaContent
import com.example.gblorchos.screens.XogadoresContent
import com.example.gblorchos.ui.elements.AppTopBar
import com.example.gblorchos.ui.elements.DrawerContent
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

    val xogadorViewModel: XogadorViewModel = viewModel()
    val resultadoViewModel: ResultadoViewModel = viewModel()

    LaunchedEffect(Unit) {
        xogadorViewModel.loadXogadores()
        resultadoViewModel.loadResultados()
    }

    val xogadores by xogadorViewModel.xogadores.collectAsState()
    val resultados by resultadoViewModel.resultados.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        Scaffold(
            topBar = { AppTopBar(onMenuClick = { scope.launch { drawerState.open() } }) },
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController, startDestination = "principal") {
                        composable("principal") { MainContent(navController = navController) }
                        composable("xogadores") {
                            XogadoresContent(
                                xogadores = xogadores,
                                modifier = Modifier.fillMaxSize(),
                                xogadorViewModel = xogadorViewModel
                            )
                        }
                        composable("eventos") { EventosContent() }
                        composable("resultados") { ResultadosContent(resultados = resultados) }
                        composable("tenda") { TendaContent() }
                        composable("add_player") {
                            AddPlayerDialog(
                                onDismiss = { navController.popBackStack() },
                                onSave = { name, position, birthDate, points ->
                                    val xogador = Xogador(
                                        nome = name,
                                        posicion = position,
                                        fechaNacemento = birthDate.toLong(),
                                        puntos = points.toInt(),
                                        equipoId = 5
                                    )
                                    xogadorViewModel.insertXogador(xogador)
                                },
                                xogadorViewModel = xogadorViewModel
                            )
                        }

                    }
                }
            }
        )
    }
}

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
                        composable("principal") { MainContent() }
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




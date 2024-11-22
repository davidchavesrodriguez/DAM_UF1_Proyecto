package com.example.gblorchos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.gblorchos.data.DatabaseProvider
import com.example.gblorchos.data.entities.Xogador
import com.example.gblorchos.ui.theme.GBLorchosTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var databaseProvider: DatabaseProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializar la base de datos
        databaseProvider = DatabaseProvider(this)

        // Usar coroutine para insertar xogadores con tus propios datos
        lifecycleScope.launch {
            // Crear una lista de xogadores con datos personalizados
            val xogadores = listOf(
                Xogador(nome = "Josué Mariño Places", posicion = "Porteiro", fechaNacemento = 1234567890L, puntos = 1),
                Xogador(nome = "José Antonio Chaves Mosquera", posicion = "Portero", fechaNacemento = 9876543210L, puntos = 0),
                Xogador(nome = "David Chaves Rodríguez", posicion = "Medio", fechaNacemento = 1122334455L, puntos = 19),
                Xogador(nome = "Luis", posicion = "Medio", fechaNacemento = 9988776655L, puntos = 180),
                Xogador(nome = "Eva", posicion = "Delantero", fechaNacemento = 6677889900L, puntos = 210),
                Xogador(nome = "Miguel", posicion = "Medio", fechaNacemento = 5544332211L, puntos = 170),
                Xogador(nome = "Sofía", posicion = "Defensa", fechaNacemento = 2233445566L, puntos = 140),
                Xogador(nome = "Manuel", posicion = "Delantero", fechaNacemento = 3344556677L, puntos = 160),
                Xogador(nome = "Martina", posicion = "Portero", fechaNacemento = 4455667788L, puntos = 130),
                Xogador(nome = "Pedro", posicion = "Defensa", fechaNacemento = 5566778899L, puntos = 190),
                Xogador(nome = "Laura", posicion = "Medio", fechaNacemento = 6677889900L, puntos = 140),
                Xogador(nome = "Felipe", posicion = "Delantero", fechaNacemento = 7788991122L, puntos = 220),
                Xogador(nome = "Clara", posicion = "Defensa", fechaNacemento = 8899002233L, puntos = 100),
                Xogador(nome = "Antonio", posicion = "Medio", fechaNacemento = 9900113344L, puntos = 210),
                Xogador(nome = "Marta", posicion = "Portero", fechaNacemento = 1234567890L, puntos = 170),
                Xogador(nome = "Javier", posicion = "Delantero", fechaNacemento = 9876543210L, puntos = 150),
                Xogador(nome = "Isabel", posicion = "Defensa", fechaNacemento = 1122334455L, puntos = 180),
                Xogador(nome = "Ricardo", posicion = "Medio", fechaNacemento = 9988776655L, puntos = 160),
                Xogador(nome = "Pablo", posicion = "Delantero", fechaNacemento = 6677889900L, puntos = 200),
                Xogador(nome = "Victoria", posicion = "Portero", fechaNacemento = 5544332211L, puntos = 130)
            )

            // Insertar todos los xogadores a la base de datos
            databaseProvider.xogadorDao.insertAllXogadores(xogadores)

            // Actualiza el estado con los xogadores recuperados
            setContent {
                GBLorchosTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            xogadores = xogadores, // Pasa la lista de xogadores aquí
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(xogadores: List<Xogador>, modifier: Modifier = Modifier) {
    // Mostrar los xogadores recuperados
    val xogadoresText = xogadores.joinToString(separator = "\n") {
        "Xogador: ${it.nome}, ${it.posicion}, Puntos: ${it.puntos}"
    }

    Text(
        text = "Xogadores:\n$xogadoresText",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // Para la previsualización, puedes usar datos de ejemplo
    val xogadores = listOf(
        Xogador(nome = "Julio", posicion = "Medio", fechaNacemento = System.currentTimeMillis(), puntos = 100)
    )
    GBLorchosTheme {
        Greeting(xogadores = xogadores)
    }
}

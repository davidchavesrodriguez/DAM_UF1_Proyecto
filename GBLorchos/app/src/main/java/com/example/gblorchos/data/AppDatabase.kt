package com.example.gblorchos.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gblorchos.data.dao.ResultadoDao
import com.example.gblorchos.data.database.EquipoDao
import com.example.gblorchos.data.database.XogadorDao
import com.example.gblorchos.data.entities.Equipo
import com.example.gblorchos.data.entities.Resultado
import com.example.gblorchos.data.entities.Xogador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Xogador::class, Equipo::class, Resultado::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun xogadorDao(): XogadorDao
    abstract fun equipoDao(): EquipoDao
    abstract fun resultadoDao(): ResultadoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Chamamos insercion inicial de datos
                            CoroutineScope(Dispatchers.IO).launch {
                                val dbInstance = getDatabase(context)
                                insertInitialData(
                                    dbInstance.xogadorDao(), dbInstance.equipoDao(),
                                    dbInstance.resultadoDao()
                                )
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Método para insertar los datos iniciales de equipos y jugadores
        suspend fun insertInitialData(xogadorDao: XogadorDao, equipoDao: EquipoDao, resultadoDao: ResultadoDao) {
            // Insertar equipos e xogadores de Lorchos
            val equipos = listOf(
                Equipo(nombre = "Auriense F.G.", ubicacion = "Ourense (Ourense)", imagen = "auriense"),
                Equipo(nombre = "Estrela Vermelha F.G.", ubicacion = "Compostela (A Coruña)", imagen = "estrela"),
                Equipo(nombre = "Fillos de Breogán", ubicacion = "A Coruña (A Coruña)", imagen = "fillos"),
                Equipo(nombre = "Keltoi GAC", ubicacion = "Vigo (Pontevedra)", imagen = "keltoi"),
                Equipo(nombre = "Lorchos", ubicacion = "Zona do Barbanza (A Coruña)", imagen = "escudo"),
                Equipo(nombre = "Lume de Beltane", ubicacion = "Poio (Pontevedra)", imagen = "lume"),
                Equipo(nombre = "Turonia Gondomar F.G.", ubicacion = "Gondomar (Pontevedra)", imagen = "turonia"),
                Equipo(nombre = "Estrela Gaels", ubicacion = "Compostela (A Coruña)", imagen = "estrela"),
                Equipo(nombre = "Keltoi Estibadores", ubicacion = "Vigo (Pontevedra)", imagen = "keltoi")
            )
            equipos.forEach { equipoDao.insert(it) }

            val lorchosId = equipoDao.getAllEquipos().find { it.nombre == "Lorchos" }?.id ?: return

            val xogadores = listOf(
                Xogador(nome = "Josué Mariño Places", posicion = "Porteiro", fechaNacemento = 880473374784, puntos = 1, equipoId = lorchosId),
                Xogador(nome = "José Antonio Chaves Mosquera", posicion = "Porteiro", fechaNacemento = 280878974784, puntos = 0, equipoId = lorchosId),
                Xogador(nome = "David Chaves Rodríguez", posicion = "Medio", fechaNacemento = 1038261374784, puntos = 19, equipoId = lorchosId),
                Xogador(nome = "Marcos Chaves Rodríguez", posicion = "Dianteiro", fechaNacemento = 1227606974784, puntos = 33, equipoId = lorchosId),
                Xogador(nome = "Iker Calvo Gómez", posicion = "Defensa", fechaNacemento = 1038261374784, puntos = 0, equipoId = lorchosId),
                Xogador(nome = "Mateo Boullón Fandiño", posicion = "Medio", fechaNacemento = 1038261374784, puntos = 10, equipoId = lorchosId),
                Xogador(nome = "Samuel López Fraga", posicion = "Medio", fechaNacemento = 943588574784, puntos = 20, equipoId = lorchosId),
                Xogador(nome = "Abel Romero Vidal", posicion = "Dianteiro", fechaNacemento = 785800574784, puntos = 30, equipoId = lorchosId),
                Xogador(nome = "Julio Aller Acuña", posicion = "Medio", fechaNacemento = 1038261374784, puntos = 0, equipoId = lorchosId),
                Xogador(nome = "Alejandro Sánchez Collazo", posicion = "Medio", fechaNacemento = 785800574784, puntos = 2, equipoId = lorchosId),
                Xogador(nome = "Rubén Sánchez Collazo", posicion = "Dianteiro", fechaNacemento = 1006703774784, puntos = 12, equipoId = lorchosId),
                Xogador(nome = "Santiago Rubén Dieste", posicion = "Defensa", fechaNacemento = 280878974784, puntos = 0, equipoId = lorchosId),
                Xogador(nome = "Marcelino Álvarez González", posicion = "Defensa", fechaNacemento = 154648574784, puntos = 0, equipoId = lorchosId),
                Xogador(nome = "Javier Delgado Mariño", posicion = "Defensa", fechaNacemento = 754242974784, puntos = 1, equipoId = lorchosId),
                Xogador(nome = "Daniel Piñeiro Gómez", posicion = "Defensa", fechaNacemento = 943588574784, puntos = 0, equipoId = lorchosId),
                Xogador(nome = "Diego Seoane Gesto", posicion = "Medio", fechaNacemento = 880473374784, puntos = 0, equipoId = lorchosId),
                Xogador(nome = "Roberto Ferreira Guerra", posicion = "Defensa", fechaNacemento = 628012574784, puntos = 1, equipoId = lorchosId),
                Xogador(nome = "Patrick Savage", posicion = "Defensa", fechaNacemento = 1069818974784, puntos = 2, equipoId = lorchosId),
                Xogador(nome = "Pablo Miragaya López", posicion = "Medio", fechaNacemento = 249321374784, puntos = 1, equipoId = lorchosId),
                Xogador(nome = "Sofía Mariño García", posicion = "Defensa", fechaNacemento = 1006703774784, puntos = 3, equipoId = lorchosId),
                Xogador(nome = "Clara Piñeiro Ordóñez", posicion = "Medio", fechaNacemento = 912030974784, puntos = 2, equipoId = lorchosId),
                Xogador(nome = "Berta Loira Cobs", posicion = "Medio", fechaNacemento = 1038261374784, puntos = 1, equipoId = lorchosId),
                Xogador(nome = "Lía Pérez Moreiras", posicion = "Dianteiro", fechaNacemento = 1132934174784, puntos = 6, equipoId = lorchosId)
            )
            xogadorDao.insertAll(xogadores)

            // Insertar resultados
            val resultados = listOf(
                Resultado(equipo1Id = 5, equipo2Id = 9, marcadorEquipo1 = 25, marcadorEquipo2 = 43),
                Resultado(equipo1Id = 1, equipo2Id = 5, marcadorEquipo1 = 61, marcadorEquipo2 = 30),
                Resultado(equipo1Id = 5, equipo2Id = 6, marcadorEquipo1 = 21, marcadorEquipo2 = 16)
            )
            try {
                resultadoDao.insertResultados(resultados)
                Log.d("ResultadoViewModel", "Resultados insertados correctamente.")
            } catch (e: Exception) {
                Log.e("ResultadoViewModel", "Error al insertar resultados: ${e.message}")
            }

        }

    }
}

package com.ucaldas.perfil


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ucaldas.perfil.ui.theme.PerfilUsuarioTheme
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucaldas.perfil.MainActivity.CancionFav

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation()
                }
            }
        }
    }

    @Composable
    fun PerfilScreen() {

        var publicaciones by rememberSaveable {
            mutableStateOf(0)
        }

        var nombre by rememberSaveable {
            mutableStateOf("Ana Gómez")
        }

        var editando by rememberSaveable {
            mutableStateOf(false)
        }

        var borrador by rememberSaveable {
            mutableStateOf("")
        }

        val logros = remember {
            mutableStateListOf("Primer commit")
        }

         val listaCanciones = listOf(
            CancionFav( 1, 1, "Midnight City", "M83", "Synth pop"),
            CancionFav(2, 2, "You give me something", "Jamiroquai", "Funk"),
            CancionFav(3, 3, "Motherboard", "Daft Punk", "Space ambient"),
            CancionFav(4, 4, "Something about us", "Daft Punk", "R&B/Soul/Funk"),
            CancionFav(5, 5, "Doin' it Rignt", "Daft Punk", "ElectroPop"),
            CancionFav(6, 6, "Lady - Hear me Tonight", "Modjo", "French House / Nu-Disco"),
            CancionFav(7, 7, "Tráeme La Noche (Bring On The Night)", "Gustavo Cerati, Andy Summers", "Rock"),
            CancionFav(8, 8, "Voyager", "Daft Punk", "French House/ Disco"),
            CancionFav(9, 9, "The way it is", "Donavon Frankenreiter", "Surf rock"),
            CancionFav(10, 10, "Elevators(Me & You)", "Outkast", "Hip Hop"),
            CancionFav(11, 11, "Enjoy The Silence", "Depeche Mode", "Synth pop/ Rock"),
            CancionFav(12, 12, "Money Trees", "Kendrick Lamar", "Hip Hop"),
            CancionFav(13, 13, "Californication", "Red Hot Chilli Peppers", "Rock alternativo"),
            CancionFav(14, 14, "Mirror", "Kendrick Lamar", "Hip Hop"),
            CancionFav(15, 15, "Signos", "Soda Stereo", "Rock"),
            CancionFav(16, 16, "Resonance", "Home", "Synthwave/Vaporwave"),
            CancionFav(17, 17, "Love", "Kid Cudi", "Hip Hop"),
            CancionFav(18, 18, "Así de grandes son las ideas ", "Calle 13", "Hip Hop"),
            CancionFav(19, 19, "Black And Yellow", "Wiz Khalifa", "Hip Hop"),
            CancionFav(20, 20, "Vivid Dreams", "Kaytranada", "R&B Electrónico")
         )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = inicialesDe(nombre),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = nombre.ifBlank { "Usuario sin nombre" },
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Ingeniería de Sistemas",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    borrador = nombre
                    editando = !editando
                }
            ) {
                Text(
                    if (editando)
                        "Cancelar"
                    else
                        "Editar perfil"
                )
            }

            AnimatedVisibility(
                visible = editando
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {

                    OutlinedTextField(
                        value = borrador,
                        onValueChange = {
                            borrador = it
                        },
                        label = {
                            Text("Nombre")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = {
                            nombre = borrador.ifBlank {
                                "Usuario sin nombre"
                            }
                            editando = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Guardar")
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        "Publicaciones",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "$publicaciones",
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                if (publicaciones > 0)
                                    publicaciones--
                            },
                            enabled = publicaciones > 0
                        ) {
                            Text("-")
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = {
                                publicaciones++
                            }
                        ) {
                            Text("+")
                        }
                    }

                    @Composable
                    fun SeccionLogros(logros: MutableList<String>) {

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                "Logros",
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            logros.forEach { logro ->
                                Text(
                                    text = "• $logro",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedButton(
                                onClick = {
                                    logros.add("Nuevo logro ${logros.size + 1}")
                                    Log.d(
                                        "Taller4",
                                        "Tamaño de la lista: ${logros.size}"
                                    )
                                }
                            ) {
                                Text("Agregar logro")
                            }
                        }
                    }
                }
            }
        }
    }

    data class CancionFav( val id: Int, val top: Int,  val nombreCancion: String, val Artista: String,  val genero: String)

    private fun inicialesDe(nombre: String): String =
        nombre.trim()
            .split(" ")
            .filter { it.isNotBlank() }
            .take(2)
            .joinToString("") {
                it.first().uppercase()
            }
            .ifBlank { "?" }
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val listaCanciones = listOf(
        MainActivity.CancionFav(1, 1, "Midnight City", "M83", "Synth Pop"),
        MainActivity.CancionFav(2, 2, "You Give Me Something", "Jamiroquai", "Funk"),
        MainActivity.CancionFav(3, 3, "Motherboard", "Daft Punk", "Space Ambient"),
        MainActivity.CancionFav(4, 4, "Something About Us", "Daft Punk", "Funk"),
        MainActivity.CancionFav(5, 5, "Doin' it Rignt", "Daft Punk", "ElectroPop"),
        MainActivity.CancionFav(6, 6, "Lady - Hear me Tonight", "Modjo", "French House / Nu-Disco"),
        MainActivity.CancionFav(7, 7, "Tráeme La Noche (Bring On The Night)", "Gustavo Cerati, Andy Summers", "Rock"),
        MainActivity.CancionFav(8, 8, "Voyager", "Daft Punk", "French House/ Disco"),
        MainActivity.CancionFav(9, 9, "The way it is", "Donavon Frankenreiter", "Surf rock"),
        MainActivity.CancionFav(10, 10, "Elevators(Me & You)", "Outkast", "Hip Hop"),
        MainActivity.CancionFav(11, 11, "Enjoy The Silence", "Depeche Mode", "Synth pop/ Rock"),
        MainActivity.CancionFav(12, 12, "Money Trees", "Kendrick Lamar", "Hip Hop"),
        MainActivity.CancionFav(13, 13, "Californication", "Red Hot Chilli Peppers", "Rock alternativo"),
        MainActivity.CancionFav(14, 14, "Mirror", "Kendrick Lamar", "Hip Hop"),
        MainActivity.CancionFav(15, 15, "Signos", "Soda Stereo", "Rock"),
        MainActivity.CancionFav(16, 16, "Resonance", "Home", "Synthwave/Vaporwave"),
        MainActivity.CancionFav(17, 17, "Love", "Kid Cudi", "Hip Hop"),
        MainActivity.CancionFav(18, 18, "Así de grandes son las ideas ", "Calle 13", "Hip Hop"),
        MainActivity.CancionFav(19, 19, "Black And Yellow", "Wiz Khalifa", "Hip Hop"),
        MainActivity.CancionFav(20, 20, "Vivid Dreams", "Kaytranada", "R&B Electrónico")
    )

    NavHost(
        navController = navController,
        startDestination = "lista"
    ) {

        composable("lista") {

            ListaCancionesScreen(
                canciones = listaCanciones,
                onCancionClick = { id ->

                    navController.navigate("detalle/$id") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = "detalle/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val id =
                backStackEntry.arguments?.getInt("id") ?: 0

            val cancion =
                listaCanciones.firstOrNull {
                    it.id == id
                }

            if (cancion != null) {

                DetalleCancionScreen(
                    cancion = cancion,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
fun ListaCancionesScreen(
    canciones: List<MainActivity.CancionFav>,
    onCancionClick: (Int) -> Unit
) {

    LazyColumn {

        item {
            Text (
                text = "Mis Top 20 Canciones Favoritas",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }

        items(
            items = canciones,
            key = { it.id }
        ) { cancion ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    onCancionClick(cancion.id)
                }
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = cancion.nombreCancion,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = cancion.Artista,
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }
        }
    }
}

@Composable
fun DetalleCancionScreen(
    cancion: MainActivity.CancionFav,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = cancion.nombreCancion,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Artista: ${cancion.Artista}"
        )

        Text(
            text = "Género: ${cancion.genero}"
        )

        Text(
            text = "Top: ${cancion.top}"
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = onBack
        ) {
            Text("Volver")
        }
    }
}


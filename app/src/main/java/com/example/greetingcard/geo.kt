package com.example.greetingcard

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.*
import kotlin.coroutines.resume

class Geo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        solicitarPermisosUbicacion(this)

        setContent {
            MostrarUbicacion()
        }
    }
}

class Ubicacion (private val context: ComponentActivity) {
    private val dondeEstoyParado: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    suspend fun traerUnicacionActual(): Location? {
        return suspendCancellableCoroutine { sigue ->
            if (ContextCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                sigue.resume(null)
                return@suspendCancellableCoroutine
            }

            dondeEstoyParado.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { location ->
                    sigue.resume(location)
                }.addOnFailureListener {
                    sigue.resume(null)
                }
        }
    }
}

fun solicitarPermisosUbicacion(activity: ComponentActivity) {
    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            1001
        )
    }
}

@Composable
fun MostrarUbicacion() {
    val context = LocalContext.current as ComponentActivity
    val locationService = remember { Ubicacion(context) }
    var coordenadas by remember { mutableStateOf("Ubicación no disponible") }

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val location = locationService.traerUnicacionActual()
            withContext(Dispatchers.Main) {
                coordenadas = "Latitud: ${location?.latitude ?: "Desconocida"}\nLongitud: ${location?.longitude ?: "Desconocida"}"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ubicación Actual", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = coordenadas)
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val location = locationService.traerUnicacionActual()
                withContext(Dispatchers.Main) {
                    coordenadas = "Latitud: ${location?.latitude ?: "Desconocida"}\nLongitud: ${location?.longitude ?: "Desconocida"}"
                }
            }
        }) {
            Text("Actualizar Ubicación")
        }
    }
}



// cordenadas
// https://www.youtube.com/watch?v=k_XaQAVua4A&ab_channel=Programaci%C3%B3nAndroidbyAristiDevs
// no corrio el paso 2 del video que es mostrar el mapa de google por que el API al activarla en consola de google pide meter tarjeta de credito y no quiero dejar mi tarjeta
//video de las rotunas esta en la lista 2
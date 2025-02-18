package com.example.greetingcard

import android.os.Bundle
 import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.ui.unit.dp

import androidx.compose.material3.Button
 import androidx.compose.material3.OutlinedTextField
 import androidx.compose.material3.Text
 import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.content.Intent
import android.speech.RecognizerIntent
 import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

  class Chat : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaPrincipal8()
        }
    }
}

@Composable
fun PantallaPrincipal8() {
    val viewModel = remember { ModeloVistaChat() }
    cajaChat(viewModel = viewModel, remitente = "Falta Asignar Usuario de la sesion ")
}

 data class TipoMensaje(
    val id: String = "",
    val contenido: String = "",
    val quienEnvia: String = "",
    val sincroMensaje: Long = System.currentTimeMillis()
)

 class ModeloVistaChat {
    private val db = FirebaseFirestore.getInstance()
    private val mensajesbd = db.collection("chats")
        .document("todochat")
        .collection("mensaje")

    var mensaje by mutableStateOf("")
    var listamensajes = mutableStateListOf<TipoMensaje>()
        private set

    init {
        escucharMensajes()
    }

    fun enviarMensaje(quienEnvia: String) {
        if (mensaje.isNotBlank()) {
            val nuevoMensaje = TipoMensaje(
                contenido = mensaje,
                quienEnvia = quienEnvia,
                sincroMensaje = System.currentTimeMillis()
            )

            mensajesbd.add(nuevoMensaje)


            mensaje = ""
        }
    }

    private fun escucharMensajes() {
        mensajesbd.orderBy("sincroMensaje")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                     return@addSnapshotListener
                }

                val mensajes = snapshot?.documents?.map { doc ->
                    doc.toObject(TipoMensaje::class.java) ?: TipoMensaje()
                } ?: emptyList()

                listamensajes.clear()
                listamensajes.addAll(mensajes)
            }
    }
}



@Composable
fun cajaChat(viewModel: ModeloVistaChat, remitente: String) {

     val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            val data = result.data
            val resultados = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val textoReconocido = resultados?.get(0) ?: "No se reconoció la voz"
            viewModel.mensaje = textoReconocido
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
         LazyColumn(
            modifier = Modifier.weight(1f).padding(8.dp),
            reverseLayout = false
        ) {
            items(viewModel.listamensajes) { mensaje ->
                BotonMensaje(
                    mensaje = mensaje,
                    esPropio = mensaje.quienEnvia == remitente
                )


            }
        }

         Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
             OutlinedTextField(
                value = viewModel.mensaje,
                onValueChange = { viewModel.mensaje = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Escribe un mensaje...") }
            )

             Button(
                onClick = {
                    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES")
                        putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla ahora...")
                    }
                    speechRecognizerLauncher.launch(intent)
                },
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text("🎙️")
            }

             Button(
                onClick = { viewModel.enviarMensaje(remitente) },
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text("Enviar")
            }
        }
    }
}


 @Composable
fun BotonMensaje(mensaje: TipoMensaje, esPropio: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = if (esPropio) Arrangement.End else Arrangement.Start
    )  {
            Text(
                text = "${mensaje.quienEnvia}: ${mensaje.contenido}",
                modifier = Modifier.padding(8.dp),
             )

    }
}



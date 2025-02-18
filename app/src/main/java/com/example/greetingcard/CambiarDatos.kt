package com.example.greetingcard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class CambiarDatos : ComponentActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaPrincipal3(auth, firestore)
        }
    }
}

@Composable
fun PantallaPrincipal3(auth: FirebaseAuth, firestore: FirebaseFirestore) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(id = R.drawable.fondo), contentScale = ContentScale.Crop)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            contraseñaRecuperaLogo()
            Spacer(modifier = Modifier.height(8.dp))
            RecuperacionConta(auth, firestore)
            botomLogin3()
        }
    }
}

@Composable
fun RecuperacionConta(auth: FirebaseAuth, firestore: FirebaseFirestore) {
    val usuarioActual = auth.currentUser

    var entradaNombre by remember { mutableStateOf("") }
    var mensajeRecuperacion by remember { mutableStateOf("") }
    var mostrarAlerta by remember { mutableStateOf(false) }
    var exitoRecuperacion by remember { mutableStateOf(false) }

    TextField(
        value = entradaNombre,
        onValueChange = { entradaNombre = it },
        label = { Text("Nuevo Nombre de Usuario") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick = {
            if (usuarioActual != null) {
                val userId = usuarioActual.uid
                if (entradaNombre.isNotBlank()) {
                    firestore.collection("usuarios")
                        .document(userId)
                        .set(mapOf("nombre" to entradaNombre), SetOptions.merge())
                        .addOnSuccessListener {
                            mensajeRecuperacion = "Nombre actualizado correctamente"
                            mostrarAlerta = true
                            exitoRecuperacion = true
                        }

                } else {
                    mensajeRecuperacion = "El nombre no puede estar vacío"
                    mostrarAlerta = true
                    exitoRecuperacion = false
                }
            } else {
                mensajeRecuperacion = "error de sesion"
                mostrarAlerta = true
                exitoRecuperacion = false
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text("Actualizar Nombre")
    }

    if (mostrarAlerta) {
        AlertDialog(
            onDismissRequest = { mostrarAlerta = false },
            title = { Text("Resultado") },
            text = { Text(mensajeRecuperacion) },
            confirmButton = {
                Button(
                    onClick = {
                        mostrarAlerta = false
                        if (exitoRecuperacion) {
                            mostrarAlerta
                        }
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}

@Composable
fun contraseñaRecuperaLogo() {
    Image(
        painter = painterResource(id = R.drawable.logo_registro),
        contentDescription = "Logo de registro"
    )
}

@Composable
fun botomLogin3() {
    val enviarLogin = LocalContext.current

    Button(
        onClick = {
            val enviarLogin2 = Intent(enviarLogin, MainActivity::class.java)
            enviarLogin.startActivity(enviarLogin2)
        },
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text("Login")
    }
}

// se utilizada el metodod de firestore para actualizar datos de nombre y se cambia en firestore las reglas
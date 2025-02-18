package com.example.greetingcard
import com.google.firebase.auth.FirebaseAuth

import android.content.Intent
import android.os.Bundle
 import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
 import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
 import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.FirebaseFirestore

class RecuperarContraseña : ComponentActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            PantallaPrincipal4(auth,firestore)

        }
    }
}


@Composable
fun PantallaPrincipal4(auth: FirebaseAuth, firestore: FirebaseFirestore) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(id = R.drawable.fondo),  contentScale = ContentScale.Crop)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            BotonRecuperacion(auth,firestore )

            botomLogin2()
        }
    }
}






@Composable
fun BotonRecuperacion(auth: FirebaseAuth, firestore: FirebaseFirestore ) {
    val enviarLogin = LocalContext.current
    var mensajeRecuperacion by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var mostrarAlerta by remember { mutableStateOf(false) }
    var exitoRecuperacion by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
         TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

         Button(
            onClick = {
                if (email.isNotBlank( )) {
                    firestore.collection("usuarios")
                        .whereEqualTo("email", email)
                        .get()
                        .addOnSuccessListener { documents ->
                            if (!documents.isEmpty) {

                                auth.sendPasswordResetEmail(email)
                                    .addOnSuccessListener {
                                        mensajeRecuperacion = "Se ha enviado un correo de recuperación"
                                        mostrarAlerta = true
                                        exitoRecuperacion = true

                                    }

                            } else {

                                mensajeRecuperacion = "Correo no registrado en nuestra base de datos"
                                mostrarAlerta = true
                                exitoRecuperacion = false
                            }
                        }

                }
            },
             modifier = Modifier
                 .fillMaxWidth()
                 .height(50.dp)
         ) {
             Text("Recuperar contraseña")
         }

        if (mostrarAlerta) {
            AlertDialog(
                onDismissRequest = {   },
                title = { Text("Recuperación") },
                text = { Text(mensajeRecuperacion) },
                confirmButton = {
                    Button(
                        onClick = {
                            mostrarAlerta = false
                            if (exitoRecuperacion) {
                                val enviarLogin2 = Intent(enviarLogin, MainActivity::class.java)
                                enviarLogin.startActivity(enviarLogin2)
                            }
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}

@Composable
fun botomLogin2() {
    val enviarLogin = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
         Button(
            onClick = {
                val enviarLogin2 = Intent(enviarLogin, MainActivity::class.java)
                enviarLogin.startActivity(enviarLogin2)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Login")
        }
    }
}
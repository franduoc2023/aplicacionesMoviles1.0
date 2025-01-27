package com.example.greetingcard

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

class RecuperarContraseña : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            PantallaPrincipal4()

        }
    }
}

@Preview
@Composable
fun PantallaPrincipal4() {
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


            BotonRecuperacion()

            botomLogin2()
        }
    }
}






@Composable
fun BotonRecuperacion() {
    val enviarLogin = LocalContext.current
    var mensajeRecuperacion by remember { mutableStateOf("") }
    val correosRegistrados = listOf(
        "francisco@gmail.com",
        "maria@gmail.com",
        "juan@gmail.com",
        "ana@gmail.com"
    )
    var email by remember { mutableStateOf("") }
    var mostrarAlerta by remember { mutableStateOf(false) }

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
                if (correosRegistrados.contains(email)) {
                    mensajeRecuperacion = "Email Correcto, se enviare link de recuperacion"
                } else {
                    mensajeRecuperacion = "El email no existe."
                }
                mostrarAlerta = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Recuperar contraseña")
        }


        if (mostrarAlerta) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text("Recuperación") },
                text = { Text(mensajeRecuperacion) },
                confirmButton = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                if (correosRegistrados.contains(email)) {
                                    val enviarLogin2 = Intent(enviarLogin, MainActivity::class.java)
                                    enviarLogin.startActivity(enviarLogin2)
                                    mostrarAlerta = false
                                } else {
                                    mensajeRecuperacion = "Correo no registrado"
                                }
                                mostrarAlerta = false
                            }
                        ) {
                            Text("Aceptar")
                        }
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
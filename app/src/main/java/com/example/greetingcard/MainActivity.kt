package com.example.greetingcard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        solicitarPermisosUbicacion(this)

        setContent {
            val viewModel: Metodos = viewModel()
            PantallaPrincipal(viewModel)
        }
    }
}

@Composable
fun PantallaPrincipal(viewModel: Metodos) {
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
            Spacer(modifier = Modifier.height(100.dp))

            Logo2()

            Spacer(modifier = Modifier.height(32.dp))

            entradaDatos(viewModel )

            Spacer(modifier = Modifier.height(32.dp))

            Registro()
            Spacer(modifier = Modifier.height(16.dp))

            Recuperar()
        }
    }
}



@Composable
fun Logo2() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo de la aplicación",
        modifier = Modifier
            .size(250.dp)
            .padding(top = 100.dp)
            .padding(bottom = 16.dp)
    )
}

@Composable
fun entradaDatos(viewModel: Metodos) {
    val context = LocalContext.current

    var entradaEmail by viewModel.entradaEmail
    var entradaPassword by viewModel.entradaPassword
    var ActivacionAlerta by viewModel.ActivacionAlerta
    var mostrarCualquierAlerta by viewModel.mostrarCualquierAlerta
    var loginExitoso by viewModel.loginExitoso
    var navegarChat by viewModel.navegarChat

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = entradaEmail,
            onValueChange = { entradaEmail = it },
            label = { Text("Correo electrónico") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = entradaPassword,
            onValueChange = { entradaPassword = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.iniciarSesion()
            },
            modifier = Modifier
                .padding(5.dp)
                .width(250.dp)
                .height(50.dp)
        ) {
            Text(text = "Login")
        }

        if (mostrarCualquierAlerta) {
            AlertDialog(
                onDismissRequest = { mostrarCualquierAlerta = false },
                title = { Text(text = "Alerta") },
                text = { Text(text = ActivacionAlerta) },
                confirmButton = {
                    Button(onClick = {
                        mostrarCualquierAlerta = false
                        if (loginExitoso && navegarChat) {
                            navegarChat = false
                            val intent = Intent(context, acciones::class.java)
                            context.startActivity(intent)
                        }
                    }) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}


@Composable
fun Registro() {
    val enviarRegistro = LocalContext.current

    Button(
        onClick = {
            val enviarRegistro2 = Intent(enviarRegistro, RegistroMain::class.java)
            enviarRegistro.startActivity(enviarRegistro2)
        },
        modifier = Modifier
            .padding(5.dp)
            .width(250.dp)
            .height(50.dp)
    ) {
        Text("Registro")
    }
}



@Composable
fun Recuperar() {
    val enviarRecuperarContraseña = LocalContext.current


    Button(
        onClick = {
            val enviarRecuperarContraseña2 = Intent(enviarRecuperarContraseña,RecuperarContraseña::class.java)
            enviarRecuperarContraseña.startActivity(enviarRecuperarContraseña2)


        },
        modifier = Modifier
            .width(250.dp)
            .height(50.dp)
    ) {
        Text(text = "Recuperar Contraseña")
    }
}










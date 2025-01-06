package com.example.greetingcard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
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
import com.example.greetingcard.ui.theme.GreetingCardTheme


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
            verticalArrangement = Arrangement.Top
        ) {


            botonRecuperacion()
            botomLogin2()
        }
    }
}






@Composable
fun botonRecuperacion (){
    var mensajeRecuperacion by remember { mutableStateOf("") }
    val correosRegistrados = listOf("francisco@gmail.com", "maria@gmail.com", "juan@gmail.com", "ana@gmail.com")
    var RecuperacionExito by remember { mutableStateOf(" ") }
    var email by remember { mutableStateOf("") }
    TextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Correo electrónico") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = {
             mensajeRecuperacion = if (correosRegistrados.contains(email)) {
                "Email de recuperacion Enviado "
            } else {
                "Email no encontrado"
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text("Recuperar contraseña")
    }

    Spacer(modifier = Modifier.height(16.dp))

     Text(
        text = mensajeRecuperacion,
      )
}


@Composable
fun botomLogin2() {
    val enviarLogin = LocalContext.current

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
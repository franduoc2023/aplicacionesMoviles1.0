package com.example.greetingcard

import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
 import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

class RegistroMain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            PantallaPrincipal2()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipal2() {
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

            registroEntradaLogo()
    Spacer(modifier = Modifier.height(8.dp))
            registroEntradaCompleto()
            botomLogin()
        }
    }
}

@Composable
fun registroEntradaLogo(){
    Image(
        painter = painterResource(id = R.drawable.logo_registro),
        contentDescription = "Logo de registro",
     )


}

@Composable
fun registroEntradaCompleto() {
    var entradaNombre by remember { mutableStateOf("") }
    var entradanContraseña by remember { mutableStateOf("") }
    var entradanContraseñaConfirmacion by remember() { mutableStateOf("")}
    var email by remember() { mutableStateOf("")}

    val arregloUsuario = remember { mutableStateListOf<Pair<String, String>>() }
    var mensajeRegistro by remember { mutableStateOf("") }
    val enviarLogin = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

             TextField(
                value = entradaNombre,
                onValueChange = { entradaNombre = it },
                label = { Text("Usuario") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

             TextField(
                value = entradanContraseña,
                onValueChange = { entradanContraseña = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = entradanContraseñaConfirmacion,
                onValueChange = { entradanContraseñaConfirmacion = it },
                label = { Text("Comprobar contraseña") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(16.dp))

             Button(
                onClick = { if (entradaNombre.isNotBlank() && email.isNotBlank() &&
                    entradanContraseña == entradanContraseñaConfirmacion &&
                    entradanContraseña.isNotBlank())
                {
                    mensajeRegistro = "Usuario registrado"
                    arregloUsuario.add(entradaNombre to entradanContraseña)


                } else mensajeRegistro = "Error al registrar "


                 },
                modifier = Modifier

                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Registrar")
            }


            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = mensajeRegistro,
                modifier = Modifier.padding(8.dp)
            )

        }
    }
}



@Composable
fun botomLogin() {
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



package com.example.greetingcard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class CambiarDatos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {



        }
    }
}


@Preview
@Composable
fun PantallaPrincipal3() {
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

            contraseñaRecuperaLogo()
            Spacer(modifier = Modifier.height(8.dp))

            RecuperacionConta()
            botomLogin3()

         }
    }
}


@Composable
fun RecuperacionConta (){
    var entradaNombre by remember { mutableStateOf("") }
    var entradaContraseña by remember { mutableStateOf("") }

    TextField(
        value = entradaNombre,
        onValueChange = { entradaNombre = it },
        label = { Text("Cambiar Nombre Usuario ") },
     )

    Spacer(modifier = Modifier.height(8.dp))
    TextField(
        value = entradaContraseña,
        onValueChange = { entradaContraseña = it },
        label = { Text("Cambiar Contraseña   ") },
    )
    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = entradaContraseña,
        onValueChange = { entradaContraseña = it },
        label = { Text("Confirmar contraseña   ") },
    )

    Spacer(modifier = Modifier.height(8.dp))

    Button( {
     }, modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()
         .height(50.dp)) {
        Text("Cambiar Datos")
    }


}


@Composable
fun contraseñaRecuperaLogo(){
    Image(
        painter = painterResource(id = R.drawable.logo_registro),
        contentDescription = "Logo de registro",
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
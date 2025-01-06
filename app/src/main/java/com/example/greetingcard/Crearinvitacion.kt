package com.example.greetingcard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.GreetingCardTheme

class Crearinvitacion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            PantallaPrincipal5()

        }
    }
}
@Preview
@Composable
fun PantallaPrincipal6() {
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

            Spacer(modifier = Modifier.height(100.dp))

            Logo6()
            Spacer(modifier = Modifier.height(100.dp))
            textoInvi2()
            Spacer(modifier = Modifier.height(25.dp))

            textoMostrar()

            botonEnviar()

            botomLogin5()

        }
    }
}

@Composable
fun Logo6() {
    Image(
        painter = painterResource(id = R.drawable.qr),
        contentDescription = "Logo de la aplicación",
        modifier = Modifier
            .size(250.dp)
            .padding(top = 100.dp)
            .padding(bottom = 16.dp)
    )
}


@Composable
fun textoMostrar() {
    Text(
        text = "Hola necesito tu Ayuda podemos hablar",
        color = androidx.compose.ui.graphics.Color.White,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun textoInvi2 (){



    var mensajeMostrar by remember { mutableStateOf("") }
    TextField(
        value = mensajeMostrar,
        onValueChange = { mensajeMostrar = it },
        label = { Text("Mensaje a enviar ") }
    )



}

@Composable
fun botonEnviar(){
    val determinar = ""

Button(
onClick = {


},        modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()
        .height(50.dp)
) {

    Text("Crear Mensaje")


}



}

@Composable
fun botomLogin5() {
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
        Text("Home")
    }
}
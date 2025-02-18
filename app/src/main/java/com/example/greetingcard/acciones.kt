package com.example.greetingcard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
 import androidx.compose.material3.Button
 import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class acciones : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PantallaPrincipal7()

                }
            }
        }

@Preview
@Composable
fun PantallaPrincipal7() {
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
            botonChat()
            Spacer(modifier = Modifier.height(32.dp))
            botonChat2()
            Spacer(modifier = Modifier.height(32.dp))
            botonChat3()

         }
    }
}




@Composable
fun botonChat() {
    val enviarChat = LocalContext.current

    Button(
        onClick = {
            val enviarChat2 = Intent(enviarChat, Chat::class.java)
            enviarChat.startActivity(enviarChat2)
        },
        modifier = Modifier
            .padding(5.dp)
            .size(105.dp),
        shape = RectangleShape
    ) {
        Text(
            text = "\uD83D\uDCAC  Chat",
            fontSize = 12.sp
        )
    }
}


@Composable
fun botonChat2() {
    val enviarChat = LocalContext.current

    Button(
        onClick = {
            val enviarChat3 = Intent(enviarChat, CambiarDatos::class.java)
            enviarChat.startActivity(enviarChat3)
        },
        modifier = Modifier
            .padding(5.dp)
            .size(105.dp),
        shape = RectangleShape
    ) {
        Text(
             text = "\uD83D\uDCA1 Cambiar Datos",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun botonChat3() {
    val enviarChat = LocalContext.current

    Button(
        onClick = {
            val enviarChat4 = Intent(enviarChat, Geo::class.java)
            enviarChat.startActivity(enviarChat4)
        },
        modifier = Modifier
            .padding(5.dp)
            .size(105.dp),
        shape = RectangleShape
    ) {
        Text(
            text = "\uD83D\uDCF1 Ubicacion Celular !!!",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize()
        )
    }
}

// use unicode por que tengo problemas con las animaciones se cae la aplicacion

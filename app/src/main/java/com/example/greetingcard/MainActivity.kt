package com.example.greetingcard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaPrincipal()
        }
    }
}

@Composable
fun PantallaPrincipal() {
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

            entradaDatos()

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
fun entradaDatos() {
    var entradaUsuario by remember { mutableStateOf("") }
    var entradaPassword by remember { mutableStateOf("") }
    var ActivacionAlerta by remember { mutableStateOf("") }
    var mostrarCualquierAlerta by remember { mutableStateOf(false) }

    val arregloUsuario = arrayOf(
        "francisco" to "123456",
        "fran" to "123456",
        "felipe" to "123456",
        "antonio" to "123456",
        "kentaro" to "123456"
    )

    var loginExitoso by remember { mutableStateOf<Boolean?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = entradaUsuario,
            onValueChange = { entradaUsuario = it },
            label = { Text("Usuario") }
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
                loginExitoso = arregloUsuario.any { it.first == entradaUsuario && it.second == entradaPassword }
                ActivacionAlerta = if (loginExitoso == true) "Login Correcto" else "Login Incorrecto"
                mostrarCualquierAlerta = true
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
                onDismissRequest = { mostrarCualquierAlerta = false },  /*  onDisMissRequest es para contrar un tipo de instancia si se cierra o no cuando
                 inicializar el contenido del AlertDialog*/
                title = {
                    Text(text = "Alerta")

                },
                text = {
                    Text(text = ActivacionAlerta)
                },
                confirmButton = {
                        if (ActivacionAlerta == "Login Correcto") {
                            Button(onClick = { mostrarCualquierAlerta = false }) {
                                Text("Aceptar")


                            }
                        }else{
                            Button(onClick = { mostrarCualquierAlerta = false }) {
                                Text("Intentar de Nuevo ")


                        }
                }}
            )
/* lo importante de entender es que el click maneja false para cerrar las ventanas */
                }}
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








@Preview(showBackground = true)
@Composable
fun PreviewEntradaDatos() {
    MaterialTheme {
        PantallaPrincipal()
    }
}



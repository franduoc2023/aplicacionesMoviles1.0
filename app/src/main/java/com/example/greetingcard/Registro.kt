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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
 import androidx.compose.ui.unit.dp
 import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
 import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroMain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()
        setContent {


            PantallaPrincipal2(auth, firestore)

        }
    }
}

 @Composable
fun PantallaPrincipal2(auth:FirebaseAuth,firestore: FirebaseFirestore) {
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
            registroEntradaCompleto(auth,firestore)
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
fun registroEntradaCompleto(auth: FirebaseAuth, firestore: FirebaseFirestore) {
    var entradaNombre by remember { mutableStateOf("") }
    var entradanContraseña by remember { mutableStateOf("") }
    var entradanContraseñaConfirmacion by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var mensajeRegistro by remember { mutableStateOf("") }
    var registroExitoso by remember { mutableStateOf(false) }
    val enviarLogin = LocalContext.current
    var mostrarAlerta by remember { mutableStateOf(false) }

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
                onClick = {
                    if (entradaNombre.isNotBlank() && email.isNotBlank() &&
                        entradanContraseña == entradanContraseñaConfirmacion &&
                        entradanContraseña.isNotBlank()
                    ) {
                        auth.createUserWithEmailAndPassword(email, entradanContraseña)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val userId = auth.currentUser?.uid
                                    val usuario = hashMapOf(
                                        "nombre" to entradaNombre,
                                        "email" to email
                                    )
                                    if (userId != null) {
                                        firestore.collection("usuarios").document(userId)
                                            .set(usuario)
                                            .addOnSuccessListener {
                                                mensajeRegistro = "Usuario registrado exitosamente"
                                                registroExitoso = true
                                                mostrarAlerta = true
                                            }
                                            .addOnFailureListener {
                                                mensajeRegistro = "Error al registrar  "
                                                mostrarAlerta = true
                                            }
                                    }
                                }
                            }
                    } else {
                        mensajeRegistro = "Error: Completa todos los campos y verifica la contraseña"
                        mostrarAlerta = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Registrar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (mostrarAlerta) {
                AlertDialog(
                    onDismissRequest = { },
                    title = { Text("Registro") },
                    text = { Text(mensajeRegistro) },
                    confirmButton = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = {
                                mostrarAlerta = false
                                if (registroExitoso) {
                                    val enviarLogin2 = Intent(enviarLogin, MainActivity::class.java)
                                    enviarLogin.startActivity(enviarLogin2)
                                }
                            }) {
                                Text("Aceptar")
                            }
                        }
                    }
                )
            }
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



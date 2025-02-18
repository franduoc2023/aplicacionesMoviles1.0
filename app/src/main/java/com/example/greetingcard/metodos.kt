package com.example.greetingcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf


class Metodos : ViewModel() {
    public var auth = FirebaseAuth.getInstance()
    var navegarChat = mutableStateOf(false)

    var entradaEmail = mutableStateOf("")
    var entradaPassword = mutableStateOf("")
    var ActivacionAlerta = mutableStateOf("")
    var mostrarCualquierAlerta = mutableStateOf(false)
    var loginExitoso = mutableStateOf(false)

    fun iniciarSesionFalsa() {
        loginExitoso.value = entradaEmail.value == "valido" && entradaPassword.value == "valido"
    }



    fun iniciarSesion() {
        if (entradaEmail.value.isNotEmpty() && entradaPassword.value.isNotEmpty()) {
            viewModelScope.launch {
                auth.signInWithEmailAndPassword(entradaEmail.value, entradaPassword.value)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            ActivacionAlerta.value = "Login Correcto"
                            loginExitoso.value = true
                            navegarChat.value = true
                        } else {
                            ActivacionAlerta.value = "Usuario o contraseña incorrectos"
                            loginExitoso.value = false
                        }
                        mostrarCualquierAlerta.value = true
                    }

            }
        } else {
            ActivacionAlerta.value = "Complete todos los campos"
            mostrarCualquierAlerta.value = true
            loginExitoso.value = false
        }
    }

}

package com.example.greetingcard

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.MutablePreferences

data class Usuario(val username: String, val password: String)
/*
class MetodosUsuario(private val paraGuardado: Context) {


    suspend fun guardarUsuario(username: String, password: String) {
        paraGuardado.guardadoUsuario.edit { preferences: MutablePreferences ->
            preferences[stringPreferencesKey("username_$username")] = username
            preferences[stringPreferencesKey("password_$username")] = password
        }
    }

    fun obtenerUsuario(username: String): Flow<Usuario?> {
        return paraGuardado.guardadoUsuario.data.map { preferences ->
            val storedUsername = preferences[stringPreferencesKey("username_$username")]
            val storedPassword = preferences[stringPreferencesKey("password_$username")]
            if (storedUsername != null && storedPassword != null) {
                Usuario(storedUsername, storedPassword)
            } else {
                null
            }
        }
    }}*/
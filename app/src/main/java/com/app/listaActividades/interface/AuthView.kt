package com.app.listaActividades.`interface`

import com.app.listaActividades.model.Empleado
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthView {
    suspend fun signIn(email : String, password : String) : Task<AuthResult>

    suspend fun signUp(email : String, password : String) : Task<AuthResult>

    fun createUserInDb(user : Empleado) : Task<Void>

}
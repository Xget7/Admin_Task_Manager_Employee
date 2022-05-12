package com.app.listaActividades.presenter

import com.app.listaActividades.`interface`.AuthView
import com.app.listaActividades.model.Empleado
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AuthPresenter() : AuthView {

    private var mAuth = FirebaseAuth.getInstance()
    private var db = Firebase.database
    val usersRef = db.getReference("empleados")

    override suspend fun signIn(email: String, password: String): Task<AuthResult> {
       return mAuth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun signUp(email: String, password: String): Task<AuthResult> {
       return mAuth.createUserWithEmailAndPassword(email, password)
    }

    override  fun createUserInDb(user: Empleado): Task<Void> {
        return usersRef.child(user.uid!!).setValue(user.toMap())
    }


}
package com.app.listaActividades.Views.Auth.Register

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.listaActividades.presenter.AuthPresenter
import com.app.listaActividades.util.Common
import com.app.listaActividades.util.Screens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var gmail = mutableStateOf("")
    var password = mutableStateOf("")
    private var db = Firebase.database
    val usersRef = db.getReference("empleados")

    val state = mutableStateOf(LoginState())

    val user = FirebaseAuth.getInstance().currentUser
    private val AuthPresenter = AuthPresenter()


    fun goToAdminOrEmpleado(navController: NavController, uid : String){
        viewModelScope.launch {
                usersRef.child(uid).child("admin").get().addOnSuccessListener {
                    if (it.exists()){
                        if (it.value == true){
                            navController.popBackStack()
                            navController.navigate(Screens.AdminScreen.route)
                        }else{
                            navController.popBackStack()
                            navController.navigate(Screens.EmpleadoScreen.route)
                        }
                    }
                }.addOnFailureListener {
                    state.value = state.value.copy(isError = it.message)
                }

        }

    }

    fun loguear(email: String, password: String, navController: NavController) {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            if (
                Common.isValidString(email.trim()) &&
                Common.isValidPassword(password.trim())
            ) {
                AuthPresenter.signIn(email.trim(), password).addOnSuccessListener { auth ->
                    state.value = state.value.copy(isSuccess = true)
                    auth.user?.let {
                        goToAdminOrEmpleado(navController, it.uid)
                    }
                }.addOnFailureListener {
                    state.value = state.value.copy(isLoading = false)
                    state.value = state.value.copy(isError = it.message)
                }
            } else {
                state.value = state.value.copy(isLoading = false)
                state.value = state.value.copy(isError = "Rellena bien los campos")
            }

        }
    }

    fun dismiss() {
        state.value = state.value.copy(isError = null)
    }
}
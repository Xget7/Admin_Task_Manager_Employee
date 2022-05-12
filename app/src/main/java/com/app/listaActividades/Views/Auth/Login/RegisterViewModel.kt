package com.app.listaActividades.Views.Auth.Login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.listaActividades.model.Empleado
import com.app.listaActividades.presenter.AuthPresenter
import com.app.listaActividades.util.Common
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    var gmail = mutableStateOf("")
    var userName = mutableStateOf("")
    var password = mutableStateOf("")

    val state = mutableStateOf(RegisterState())

    val user = FirebaseAuth.getInstance().currentUser
    val loginPresenter = AuthPresenter()

    fun registrar(email: String, password: String) {
        viewModelScope.launch {

            state.value = state.value.copy(isLoading = true)
            if (
                Common.isValidString(email.trim()) &&
                Common.isValidPassword(password.trim()) &&
                Common.isValidName(userName.value.trim())
            ) {
                loginPresenter.signUp(email.trim(), password).addOnSuccessListener {
                        try {
                            val empleado = Empleado(
                                it.user!!.uid,
                                false,
                                userName.value.trim(),
                                it.user!!.email!!,
                            )
                            loginPresenter.createUserInDb(empleado).addOnSuccessListener {
                                state.value = state.value.copy(isSuccess = true)
                            }.addOnFailureListener { exception ->
                                state.value = state.value.copy(isError = exception.message)
                            }
                        } catch (e: Exception) {
                            state.value = state.value.copy(isError = e.message)
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
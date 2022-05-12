package com.app.listaActividades.Views.Auth.Register

data class LoginState(
    val isLoading: Boolean = false,
    val isError: String? = null,
    val isSuccess: Boolean = false,
)

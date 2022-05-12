package com.app.listaActividades.model

data class Empleado(
    val uid: String? = "",
    val admin: Boolean? = false,
    val name : String  = "",
    val email : String = "",
) {
    fun toMap() : HashMap<String, Any?> = hashMapOf(
        "uid" to uid,
        "name" to name,
        "admin" to admin,
        "email" to email,
    )
}
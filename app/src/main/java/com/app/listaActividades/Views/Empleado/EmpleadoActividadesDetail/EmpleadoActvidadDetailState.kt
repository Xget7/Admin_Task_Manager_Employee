package com.app.listaActividades.Views.Empleado.EmpleadoActividadesDetail

import com.app.listaActividades.model.Actividad
import com.app.listaActividades.model.Empleado

data class EmpleadoActvidadDetailState(
    val isLoading: Boolean? = false,
    val actividad: Actividad? = null,
    val isSuccess: Boolean? = false,
    val isError: String? = null,
)

package com.app.listaActividades.Views.Empleado

import com.app.listaActividades.model.Actividad
import com.app.listaActividades.model.Empleado

data class EmpleadoState(
    val isLoading: Boolean? = false,
    val actividades: List<Actividad>? = null,
    val emplead: Empleado? = null,
    val isError: String? = null,
)

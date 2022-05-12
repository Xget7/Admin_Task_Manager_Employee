package com.app.listaActividades.Views.Administrador.editActivityAdmin

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.listaActividades.Views.Administrador.createActivityAdmin.CreateActivitysState
import com.app.listaActividades.model.Actividad
import com.app.listaActividades.presenter.AdminPresenter
import com.app.listaActividades.util.Common
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivityAdminViewModel  constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    val state = mutableStateOf(EditActivityAdminState())
    private val adminPresenter = AdminPresenter()

    //variables
    val id = mutableStateOf("")
    val toId = mutableStateOf("")
    val activityClient = mutableStateOf("")
    val activityDireccion = mutableStateOf("")
    val activityTitle = mutableStateOf("")
    val activityDescription = mutableStateOf("")
    val activityType= mutableStateOf("")
    val activityTime = mutableStateOf("")
    val activityState = mutableStateOf("")

    init {
        val _currentEmpleado = savedStateHandle.get<String>(Common.EMPLEADO_ID)
        val _currentTask= savedStateHandle.get<String>(Common.TASK_ID)

        getActivityById(_currentEmpleado!!,_currentTask!! )
    }

    fun updateActivity(){
        viewModelScope.launch {
            val act= Actividad(
                id.value,
                toId.value,
                activityClient.value,
                activityDireccion.value,
                activityTitle.value,
                activityDescription.value,
                activityType.value,
                activityTime.value,
                null,
                "Pendiente",
            )
            adminPresenter.updateActivity(act.toId!!,act)
                .addOnSuccessListener { snapshot ->
                    state.value = state.value.copy(isSuccess = true)
                }.addOnFailureListener {
                    state.value = state.value.copy(errorMsg = it.message)
                }
        }
    }
    private fun getActivityById(empleadoId : String ,actividadId: String) {
        viewModelScope.launch {
            adminPresenter.getActividadById(empleadoId,actividadId )
                .addOnSuccessListener { snapshot ->
                    val actv = snapshot.getValue(Actividad::class.java)
                    parseActivity(actv!!)

                }.addOnFailureListener {
                    state.value = state.value.copy(errorMsg = it.message)
            }
        }
    }

    private fun parseActivity(actividad: Actividad) {
            toId.value = actividad.toId!!
            id.value = actividad.id!!
            activityClient.value = actividad.activityClient!!
            activityDireccion.value = actividad.activityDireccion!!
            activityTitle.value = actividad.activityTitle!!
            activityDescription.value = actividad.activityDescription!!
            activityType.value = actividad.activityType!!
            activityTime.value = actividad.activityTime!!
            activityState.value = actividad.state!!
    }

    fun dismiss() {
        state.value = state.value.copy(errorMsg = null)
    }

}
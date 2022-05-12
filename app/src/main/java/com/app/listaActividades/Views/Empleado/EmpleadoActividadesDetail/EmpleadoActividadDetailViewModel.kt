package com.app.listaActividades.Views.Empleado.EmpleadoActividadesDetail

import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.listaActividades.model.Actividad
import com.app.listaActividades.presenter.EmpleadoPresenter
import com.app.listaActividades.util.Common
import com.app.listaActividades.util.Screens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*

class EmpleadoActividadDetailViewModel constructor(
    private var savedStateHandle : SavedStateHandle
) : ViewModel() {


    private val mAuth = FirebaseAuth.getInstance()
    private val currentUser = mAuth.currentUser!!
    private val userId = currentUser.uid
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

    val currentActividad = mutableStateOf<Actividad>(Actividad())

    val state = mutableStateOf<EmpleadoActvidadDetailState>(EmpleadoActvidadDetailState())
    private val empleadoPresenter = EmpleadoPresenter()

    init {
        val _currentActivityId = savedStateHandle.get<String>(Common.TASK_ID)
        if (_currentActivityId != null) {
            getTaskById(_currentActivityId)
        }
    }

    fun updateActivity( navController: NavController) {
        viewModelScope.launch {
            val currentDate = sdf.format(Date())
            val act = Actividad(
                id = currentActividad.value.id,
                toId = currentActividad.value.toId,
                activityTitle = currentActividad.value.activityTitle,
                activityDescription = currentActividad.value.activityDescription,
                state = "Realizada",
                activityFinishedTime = currentDate.toString(),
                activityClient = currentActividad.value.activityClient,
                activityDireccion = currentActividad.value.activityDireccion,
                activityType = currentActividad.value.activityType,
                activityTime = currentActividad.value.activityTime,
                )

            empleadoPresenter.updateActivity(act).addOnSuccessListener {
                state.value = state.value.copy(isSuccess = true)
                navController.popBackStack()
                navController.navigate(Screens.EmpleadoScreen.route)
            }.addOnFailureListener {
                state.value = state.value.copy(isError = it.message)
            }
        }
    }

    private fun getTaskById(taskId: String) {
        viewModelScope.launch {
            empleadoPresenter.getSpecifictActivity(taskId).get().addOnSuccessListener {
                if (it.exists()){
                    it.getValue(Actividad::class.java)?.let { actividad ->
                        currentActividad.value = actividad
                    }
                }else{
                    state.value = state.value.copy(isError = "Error obteniendo actividad o no existe")
                }
            }.addOnFailureListener {
                state.value = state.value.copy(isError = it.message)
            }
        }
    }


}
package com.app.listaActividades.Views.Administrador.createActivityAdmin

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.listaActividades.model.Actividad
import com.app.listaActividades.model.Empleado
import com.app.listaActividades.presenter.AdminPresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class CreateActivitysViewModel : ViewModel() {

    val state = mutableStateOf(CreateActivitysState())


    val currentEmpleadoName = mutableStateOf<String>("")

    val id = mutableStateOf("")
    val toId = mutableStateOf("")
    val activityClient = mutableStateOf("")
    val activityDireccion = mutableStateOf("")
    val activityTitle = mutableStateOf("")
    val activityDescription = mutableStateOf("")
    val activityType= mutableStateOf("")
    val activityTime = mutableStateOf("")
    val actiivtyFinishedTime = mutableStateOf("")
    val activityState = mutableStateOf("")

    private val adminPresenter = AdminPresenter()
    val opcionOfEmpleadosList = mutableStateListOf<Empleado>()

    init {
        state.value = state.value.copy(isLoading = true)
        getListOfEmpleados()

    }

    private fun getListOfEmpleados(){
        viewModelScope.launch {
            adminPresenter.getEmpleados().addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    opcionOfEmpleadosList.clear()
                    for (ds in snapshot.children) {
                        val empleado = ds.getValue(Empleado::class.java)
                        opcionOfEmpleadosList.add(empleado!!)
                    }
                    if (opcionOfEmpleadosList.isNotEmpty()){
                        state.value = state.value.copy(isSuccess = false, isLoading = false)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    state.value = state.value.copy(errorMsg = error.message)
                }
            })
        }
    }

    fun selectEmpleado(empleadoId: String){
        toId.value = empleadoId
    }

    fun createActivityForUser(){
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)

            val actividad = Actividad(
                id = System.currentTimeMillis().toString(),
                toId = if(toId.value.isNullOrEmpty()) opcionOfEmpleadosList[0].uid else toId.value,
                activityClient = activityClient.value,
                activityDireccion = activityDireccion.value,
                activityTitle = activityTitle.value,
                activityDescription = activityDescription.value,
                activityType = activityType.value,
                activityTime = activityTime.value,
                state = "Pendiente"
            )
            Log.e("Wich empleado?" , "${toId.value} and list:  ${opcionOfEmpleadosList[0].uid}")
            adminPresenter.createActivityForUser(if(toId.value.isNullOrEmpty()) opcionOfEmpleadosList[0].uid!! else toId.value, actividad).addOnSuccessListener {
                state.value = state.value.copy(isSuccess = true, isLoading = false)
            }.addOnFailureListener {
                state.value = state.value.copy(errorMsg = it.message)

            }
        }
    }

    fun dismiss() {
        state.value = state.value.copy(errorMsg = null)
    }
}
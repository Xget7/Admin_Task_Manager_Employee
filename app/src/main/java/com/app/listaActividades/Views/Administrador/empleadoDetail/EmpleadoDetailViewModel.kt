package com.app.listaActividades.Views.Administrador.empleadoDetail

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.listaActividades.model.Actividad
import com.app.listaActividades.model.Empleado
import com.app.listaActividades.presenter.AdminPresenter
import com.app.listaActividades.util.Common
import com.app.listaActividades.util.Common.Companion.swapList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import java.nio.file.Files.delete

class EmpleadoDetailViewModel constructor(
    var savedStateHandle : SavedStateHandle
) : ViewModel() {

    private val mAuth = FirebaseAuth.getInstance()
    private val currentUser = mAuth.currentUser!!
    val state = mutableStateOf(EmpleadoDetailState())

    val userName = mutableStateOf("")
    val empleadoGmail = mutableStateOf("")
    val empleadoUid = mutableStateOf("")

    val actividadesList = mutableStateListOf<Actividad>()
    val _originalList = mutableStateListOf<Actividad>()

    val currentEmpleado = mutableStateOf<Empleado>(Empleado())
    private val adminPresenter = AdminPresenter()

    var optionsList = mutableListOf("Todas","Pendientes","Realizadas")

    init {
        val _currentEmpleado = savedStateHandle.get<String>(Common.EMPLEADO_ID)
        if (_currentEmpleado != null) {
            getEpleadoData(_currentEmpleado)
            getActividades(_currentEmpleado)
        }
    }


    fun filterBy(option: String){
        when (option) {
            "Pendientes" -> {
                val _list = _originalList.filter { it.state == "Pendiente" }
                actividadesList.swapList(_list)
            }
            "Realizadas" -> {
                val _list = _originalList.filter { it.state == "Realizada" }
                actividadesList.swapList(_list)
            }
            "Todas" -> {
                actividadesList.swapList(_originalList)
            }
        }
    }



     fun eliminarActividad(empleadoId: String,actividadId : String) {
        viewModelScope.launch {
            adminPresenter.deleteActivity(empleadoId, actividadId)
                .addOnSuccessListener {
                    getActividades(empleadoId)
            }.addOnFailureListener {
                    state.value = state.value.copy(errorMsg = it.message)
            }
        }
    }

    fun deleteEmpleado(empleadoId: String) {
        viewModelScope.launch {
            adminPresenter.deleteEmpleado(empleadoId).addOnSuccessListener {
                state.value = state.value.copy(isSucess = true)
            }.addOnFailureListener {
                state.value = state.value.copy(errorMsg = it.message)
            }
        }
    }

    private fun getActividades(uid : String){
        viewModelScope.launch {
            adminPresenter.getActividadesByUserId(uid).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    actividadesList.clear()
                    _originalList.clear()
                    for (ds in snapshot.children) {
                        val actividad = ds.getValue(Actividad::class.java)
                        actividadesList.add(actividad!!)
                        _originalList.add(actividad)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    state.value = state.value.copy(errorMsg = error.message)
                }
            })
        }
    }


    private fun getEpleadoData(uid : String) {
        viewModelScope.launch {
            Log.e("LLEGO ELUID?",uid)
            adminPresenter.getEmpleadoById(uid).get().addOnSuccessListener { data ->
                currentEmpleado.value = data.getValue(Empleado::class.java)!!
                currentEmpleado.value.name.let {
                    userName.value = it
                }
                currentEmpleado.value.uid?.let {
                    empleadoUid.value = it
                }
                currentEmpleado.value.email.let {
                    empleadoGmail.value = it
                }

            }.addOnFailureListener {
                state.value = state.value.copy(errorMsg = it.message)
            }
        }
    }
}
package com.app.listaActividades.Views.Empleado

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.listaActividades.model.Actividad
import com.app.listaActividades.model.Empleado
import com.app.listaActividades.presenter.EmpleadoPresenter
import com.app.listaActividades.util.Common.Companion.swapList
import com.app.listaActividades.util.Screens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class EmpleadoViewModel : ViewModel() {

    private val mAuth = FirebaseAuth.getInstance()
    private val currentUser = mAuth.currentUser!!

    var optionsList = mutableListOf("Todas","Pendientes","Realizadas")

    val userGmail = currentUser.email
    val userUid = currentUser.uid
    val userName = mutableStateOf("")

    val currentEmpleado = mutableStateOf<Empleado?>(null)

    val actividadesList = mutableStateListOf<Actividad>()
    val _originalList = mutableStateListOf<Actividad>()


    val state = mutableStateOf<EmpleadoState>(EmpleadoState())
    private val empleadoPresenter = EmpleadoPresenter()


    init {
        getEpleadoName()
        getActivitys()
        _originalList.swapList(actividadesList)

    }

    fun logOut(navController: NavController) {
        mAuth.signOut()
        navController.popBackStack()
        navController.navigate(Screens.LoginScreen.route)
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

    private fun getActivitys(){
        viewModelScope.launch {
            empleadoPresenter.getRealTimeActivities().addValueEventListener( object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    actividadesList.clear()
                    _originalList.clear()
                    for (sp in snapshot.children) {
                        val actividad = sp.getValue(Actividad::class.java)
                        actividadesList.add(actividad!!)
                        _originalList.add(actividad)

                    }

                }
                override fun onCancelled(error: DatabaseError) {
                    state.value = state.value.copy(isError = error.message)

                }

            })
        }
    }


    private fun getEpleadoName() {
        viewModelScope.launch {
            empleadoPresenter.getEmpleado().get().addOnSuccessListener { data ->
                currentEmpleado.value = data.getValue(Empleado::class.java)
                currentEmpleado.value?.name?.let {
                    userName.value = it
                }

            }.addOnFailureListener {
                state.value = state.value.copy(isError = it.message)
            }
        }
    }


}

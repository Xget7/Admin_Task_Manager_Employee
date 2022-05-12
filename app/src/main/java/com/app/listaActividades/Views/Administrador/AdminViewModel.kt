package com.app.listaActividades.Views.Administrador

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.listaActividades.model.Empleado
import com.app.listaActividades.presenter.AdminPresenter
import com.app.listaActividades.presenter.AuthPresenter
import com.app.listaActividades.util.Screens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AdminViewModel : ViewModel() {

    val state = mutableStateOf(AdminState())
    private val mAuth = FirebaseAuth.getInstance()

    val empleadosList = mutableStateListOf<Empleado>()
    private val adminPresenter = AdminPresenter()

    init {
        getListOfEmpleados()
    }


    private fun getListOfEmpleados(){
        viewModelScope.launch {
//            state.value = state.value.copy(isLoading = true)
            adminPresenter.getEmpleados().addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    empleadosList.clear()
                    for (ds in snapshot.children) {
                        val empleado = ds.getValue(Empleado::class.java)
                        empleadosList.add(empleado!!)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    state.value = state.value.copy(errorMsg = error.message)
                }
            })
        }
    }


    fun logOut(navController: NavController) {
        mAuth.signOut()
        navController.popBackStack()
        navController.navigate(Screens.LoginScreen.route)
    }
}
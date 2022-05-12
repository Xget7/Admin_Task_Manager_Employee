package com.app.listaActividades

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.listaActividades.Views.Auth.Register.LoginScreen
import com.app.listaActividades.Views.Auth.Register.LoginViewModel
import com.app.listaActividades.Views.Administrador.AdminScreen
import com.app.listaActividades.Views.Administrador.AdminViewModel
import com.app.listaActividades.Views.Administrador.CreateActivitysAdminScreen
import com.app.listaActividades.Views.Administrador.createActivityAdmin.CreateActivitysViewModel
import com.app.listaActividades.Views.Administrador.editActivityAdmin.EditActivityAdminScreen
import com.app.listaActividades.Views.Administrador.editActivityAdmin.EditActivityAdminViewModel
import com.app.listaActividades.Views.Administrador.empleadoDetail.EmpleadoDetailScreen
import com.app.listaActividades.Views.Administrador.empleadoDetail.EmpleadoDetailViewModel
import com.app.listaActividades.Views.Empleado.EmpleadoScreen
import com.app.listaActividades.Views.Empleado.EmpleadoViewModel
import com.app.listaActividades.Views.Auth.Login.RegisterScreen
import com.app.listaActividades.Views.Auth.Login.RegisterViewModel
import com.app.listaActividades.Views.Empleado.EmpleadoActividadesDetail.EmpleadoActividadDetailScreen
import com.app.listaActividades.Views.Empleado.EmpleadoActividadesDetail.EmpleadoActividadDetailViewModel
import com.app.listaActividades.ui.ListaDeActividades
import com.app.listaActividades.util.Common
import com.app.listaActividades.util.Screens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    private var mAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user: FirebaseUser? = mAuth.currentUser

        setContent {
            ListaDeActividades {
                ListaApp(user)
            }

        }

    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListaApp(
    user : FirebaseUser?
) {

    val navController = rememberNavController()
    val navBarNavController = rememberNavController()


    NavHost(navController = navController, startDestination = Screens.LoginScreen.route) {

        composable(
            route = Screens.RegisterScreen.route
        ) {
            val viewModel = viewModel<RegisterViewModel>()
            RegisterScreen(viewModel = viewModel, navController)
        }

        composable(
            route = Screens.LoginScreen.route
        ) {
            val viewModel = viewModel<LoginViewModel>()
            LoginScreen(navController, viewModel)
        }

        composable(
            route = Screens.EmpleadoScreen.route
        ) {
           val viewModel = viewModel<EmpleadoViewModel>()
            EmpleadoScreen(navController, viewModel)
        }

        composable(
            route = Screens.EmpleadoActividadesDetailScreen.route
        ) {
            val viewModel = viewModel<EmpleadoActividadDetailViewModel>()
            EmpleadoActividadDetailScreen(viewModel,navController)
        }

        composable(
            route = Screens.AdminScreen.route
        ) {
            val viewModel = viewModel<AdminViewModel>()
            AdminScreen(viewModel, navController)
        }

        composable(
            route = Screens.AdminEmpleadoDetail.route
        ) {
            val viewModel = viewModel<EmpleadoDetailViewModel>()
            EmpleadoDetailScreen(viewModel, navController)
        }

        composable(
            route = Screens.AdminCreateActivity.route
        ) {
            val viewModel = viewModel<CreateActivitysViewModel>()
            CreateActivitysAdminScreen(viewModel, navController)
        }



        composable(
            route = Screens.AdminEditActivity.route
        ) {
            val viewModel = viewModel<EditActivityAdminViewModel>()
            EditActivityAdminScreen(viewModel, navController)
        }
    }
}
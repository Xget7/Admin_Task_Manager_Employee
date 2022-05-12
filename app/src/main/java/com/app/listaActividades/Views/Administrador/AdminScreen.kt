package com.app.listaActividades.Views.Administrador

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.listaActividades.Views.Administrador.components.EmpleadosList
import com.app.listaActividades.ui.LightBlue
import com.app.listaActividades.ui.ListaDeActividades
import com.app.listaActividades.util.Screens

@Composable
fun AdminScreen(
    viewModel: AdminViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
) {

    val lazyListScope = rememberLazyListState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = LightBlue)
                    .height(70.dp)
            ) {
                Column {
                    Row() {
                        Text(
                            text = "Bienvenido Administrador",
                            Modifier
                                .padding(8.dp),
                            fontSize = 23.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier =Modifier.width(30.dp))
                        IconButton(onClick = {
                           viewModel.logOut(navController)
                        }) {
                            Icon(imageVector = Icons.Default.Logout , contentDescription = "Logout", tint = Color.White)
                        }
                    }
                }
            }

        },
    ) {
        Box(){
            Column {
                Text(
                    text = "Empleados: ",
                    Modifier
                        .padding(8.dp),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )


                if (viewModel.state.value.isLoading == true) {
                    CircularProgressIndicator()
                }
                Divider()
                LazyColumn(state = lazyListScope){
                    items(viewModel.empleadosList){
                        Spacer(modifier = Modifier.height(15.dp))
                        EmpleadosList(empleado = it) {
                            Log.e("empleadoListOnclick", it.uid.toString())
                            navController.navigate(Screens.AdminEmpleadoDetail.passEmpleadoId(
                                it.uid!!)
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))

                    }
                }
            }

            //--------------------------------------------------BOTON FLOTANTE -------------------------------------\\
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
            ) {
                FloatingActionButton(onClick = {
                    navController.navigate(Screens.AdminCreateActivity.route)
                }, modifier = Modifier
                    .padding(
                        start = 308.dp, //MEDIDAS
                        top = 600.dp
                ), backgroundColor = LightBlue) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add activity", tint = Color.White)
                }
            }



        }





    }
}


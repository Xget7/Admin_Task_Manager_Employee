package com.app.listaActividades.Views.Administrador

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.app.listaActividades.Views.Administrador.createActivityAdmin.CreateActivitysViewModel
import com.app.listaActividades.util.Screens
import com.talhafaki.composablesweettoast.util.SweetToastUtil.SweetSuccess
import kotlinx.coroutines.delay

@Composable
fun CreateActivitysAdminScreen(
    viewModel: CreateActivitysViewModel = viewModel(),
    navController: NavController
) {

    var sweetSucess = rememberSaveable {
        mutableStateOf(false)
    }



    if (viewModel.state.value.isLoading == false) {
        var optionName: String by remember {
            mutableStateOf(viewModel.opcionOfEmpleadosList[0].name)
        }
        var expanded by remember { mutableStateOf(false) }

        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            topBar = {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }

                        Text(
                            text = "Actividad Nueva",
                            Modifier
                                .padding(top = 0.dp, bottom = 0.dp),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                }
            }
        ) {
            Column(
                Modifier
                    .padding(start = 36.dp, end = 36.dp)
                    .fillMaxSize()
            ) {


                // The text will remain intact when there is configuration changes
                var id by rememberSaveable {
                    viewModel.id
                }
                var toId by rememberSaveable {
                    viewModel.toId
                }
                var activityClient by rememberSaveable {
                    viewModel.activityClient
                }
                var activityDireccion by rememberSaveable {
                    viewModel.activityDireccion
                }
                var activityTitle by rememberSaveable {
                    viewModel.activityTitle
                }
                var activityDescription by rememberSaveable {
                    viewModel.activityDescription
                }
                var activityType by rememberSaveable {
                    viewModel.activityType
                }
                var activityTime by rememberSaveable {
                    viewModel.activityTime
                }
                var actiivtyFinishedTime by rememberSaveable {
                    viewModel.actiivtyFinishedTime
                }
                var activityState by rememberSaveable {
                    viewModel.activityState
                }



                Column(
                    Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        value = activityTitle,
                        onValueChange = { typedEmail ->
                            activityTitle = typedEmail
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            //.width(342.dp)
                            .padding(bottom = 14.dp),
                        singleLine = true,
                        label = { Text(text = "Titulo") },
                        placeholder = { Text("Informe Repacion") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Title,
                                contentDescription = "Title",
                            )
                        },
                    )

                    OutlinedTextField(
                        value = activityClient,
                        onValueChange = { typedName ->
                            activityClient = typedName
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            //.width(342.dp)
                            .padding(bottom = 14.dp),
                        singleLine = true,
                        label = { Text(text = "Cliente") },
                        placeholder = { Text("Edesur") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = "Client"
                            )
                        },
                    )


                    OutlinedTextField(
                        value = activityDireccion,
                        onValueChange = { typedPassword ->
                            activityDireccion = typedPassword
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),

                        //.width(342.dp),
                        singleLine = true,
                        label = { Text(text = "Direccion") },
                        placeholder = { Text("Av. Montes De Oca 643") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.GpsFixed,
                                contentDescription = "Direction"
                            )
                        },
                    )

                    OutlinedTextField(
                        value = activityDescription,
                        onValueChange = { typedPassword ->
                            activityDescription = typedPassword
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        //.width(342.dp),
                        singleLine = true,
                        label = { Text(text = "Descripcion") },
                        placeholder = { Text("...") },
                    )

                    OutlinedTextField(
                        value = activityType,
                        onValueChange = { typedPassword ->
                            activityType = typedPassword
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        //.width(342.dp),
                        singleLine = true,
                        label = { Text(text = "Tipo de Actividad") },
                        placeholder = { Text("Reparacion") },
                    )

                    OutlinedTextField(
                        value = activityTime,
                        onValueChange = { typedPassword ->
                            activityTime = typedPassword
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        //.width(342.dp),
                        singleLine = true,
                        label = { Text(text = "Horario") },
                        placeholder = { Text("10:30-12:30") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Timer,
                                contentDescription = "Time"
                            )
                        },
                    )


                    //Login Button

                    Spacer(modifier = Modifier.height(36.dp))

                    Row(
                        modifier = Modifier
                            .clickable {
                                expanded = !expanded
                            }
                            .padding(18.dp),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            text = optionName,
                            fontWeight = FontWeight.Medium,
                            fontSize = 17.sp
                        )
                        Icon(imageVector = Icons.Filled.ArrowDropDown, "drop down menu")
                        DropdownMenu(
                            modifier = Modifier.size(130.dp),
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            }) {

                            viewModel.opcionOfEmpleadosList.forEach { empleado ->
                                DropdownMenuItem(
                                    modifier = Modifier.width(140.dp),
                                    onClick = {
                                        viewModel.selectEmpleado(empleado.uid!!)
                                        optionName = empleado.name
                                        expanded = false
                                    }
                                ) {
                                    Text(empleado.name)
                                }
                            }
                        }
                    }


                    Button(
                        onClick = {
                            viewModel.createActivityForUser()
                        },
                        Modifier
                            .fillMaxWidth()
                            //.width(342.dp)
                            .height(64.dp)
                            .padding(bottom = 12.dp)
                    ) {
                        Text(
                            "Asignar",
                            fontSize = 17.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(26.dp))


                }
                if (sweetSucess.value) SweetSuccess(
                    message = "Tarea Añadida con exito!",
                    duration = Toast.LENGTH_SHORT,
                    padding = PaddingValues(top = 16.dp),
                    contentAlignment = Alignment.TopCenter
                )


            }

            if (viewModel.state.value.errorMsg != null) {
                Snackbar(
                    modifier = Modifier.padding(
                        top = 140.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    ),
                    actionOnNewLine = false,
                    action = {
                        TextButton(onClick = {
                            viewModel.dismiss()
                        }) {
                            Text(text = "Okey")
                        }
                    }
                ) {
                    Text(text = viewModel.state.value.errorMsg!!)
                }
            }

            if (viewModel.state.value.isSuccess == true) {
                LaunchedEffect(true) {
                    sweetSucess.value = true
                    delay(1500)
                    navController.navigate(Screens.AdminScreen.route)
                    navController.popBackStack()
                    sweetSucess.value = false
                }
            }
        }


    } else if (viewModel.state.value.isLoading == true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }


}
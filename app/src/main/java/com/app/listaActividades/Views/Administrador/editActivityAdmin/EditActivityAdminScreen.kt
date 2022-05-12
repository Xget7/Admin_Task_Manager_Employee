package com.app.listaActividades.Views.Administrador.editActivityAdmin

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.talhafaki.composablesweettoast.util.SweetToastUtil
import kotlinx.coroutines.delay

@Composable
fun EditActivityAdminScreen(
    viewModel: EditActivityAdminViewModel = viewModel(),
    navController: NavController
) {

    var sweetSucess = rememberSaveable {
        mutableStateOf(false)
    }

    if (viewModel.state.value.isLoading == false) {

        LazyColumn(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                Column(
                    Modifier
                        .padding(start = 36.dp, end = 36.dp)
                        .fillMaxSize()
                ) {

                    Column(
                        Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Row(
                            Modifier.padding(top = 16.dp)
                        ) {
                            IconButton(onClick = {
                                navController.navigateUp()
                            }) {
                                Icon(imageVector = Icons.Outlined.ArrowBackIosNew , contentDescription ="Back" )
                            }

                            Text(
                                text = "Actividad Nueva",
                                Modifier
                                    .padding(top = 9.dp, bottom = 16.dp),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }

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
                                .padding(bottom = 1.dp),
                            singleLine = true,
                            label = { Text(text = "Titulo") },
                            placeholder = { Text("Informe Repacion") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Title,
                                    contentDescription = "Title",
                                )
                            },
                            maxLines = 1
                        )

                        Spacer(modifier =  Modifier.padding(top = 16.dp))
                        OutlinedTextField(
                            value = activityClient,
                            onValueChange = { typedName ->
                                activityClient = typedName
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                //.width(342.dp)
                                .padding(bottom = 1.dp),
                            singleLine = true,
                            label = { Text(text = "Cliente") },
                            placeholder = { Text("Edesur") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = "Client"
                                )
                            },
                            maxLines = 1
                        )

                        Spacer(modifier =  Modifier.padding(top = 16.dp))

                        OutlinedTextField(
                            value = activityDireccion,
                            onValueChange = { typedPassword ->
                                activityDireccion = typedPassword
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
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
                            maxLines = 1

                        )
                        Spacer(modifier =  Modifier.padding(top = 16.dp))

                        OutlinedTextField(
                            value = activityDescription,
                            onValueChange = { typedPassword ->
                                activityDescription = typedPassword
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            //.width(342.dp),
                            singleLine = false,
                            maxLines = 10,
                            label = { Text(text = "Descripcion") },
                            placeholder = { Text("...") },
                        )
                        Spacer(modifier =  Modifier.padding(top = 16.dp))

                        OutlinedTextField(
                            value = activityType,
                            onValueChange = { typedPassword ->
                                activityType = typedPassword
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            //.width(342.dp),
                            singleLine = true,
                            label = { Text(text = "Tipo de Actividad") },
                            placeholder = { Text("Reparacion") },
                            maxLines = 1

                        )
                        Spacer(modifier =  Modifier.padding(top = 16.dp))

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
                            maxLines = 1

                        )


                        //Actualizar Button

                        Spacer(modifier = Modifier.height(36.dp))



                        Button(
                            onClick = {
                                      viewModel.updateActivity()
                            },
                            Modifier
                                .fillMaxWidth()
                                //.width(342.dp)
                                .height(64.dp)
                                .padding(bottom = 12.dp)
                        ) {
                            Text(
                                "Actualizar Actividad",
                                fontSize = 17.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(26.dp))


                    }
                    if (sweetSucess.value) SweetToastUtil.SweetSuccess(
                        message = "Success Updated!",
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
                        delay(1000)
                        navController.popBackStack()
                        navController.navigateUp()
                        sweetSucess.value = false
                    }
                }
            }

        }
    }
}
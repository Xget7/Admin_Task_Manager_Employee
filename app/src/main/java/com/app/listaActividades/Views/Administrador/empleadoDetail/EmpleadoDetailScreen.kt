package com.app.listaActividades.Views.Administrador.empleadoDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.PersonRemove
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.app.listaActividades.Views.Administrador.components.ActividadesAdminItem
import com.app.listaActividades.ui.LightBlue
import com.app.listaActividades.util.Screens

@Composable
fun EmpleadoDetailScreen(
    viewModel: EmpleadoDetailViewModel = viewModel(),
    navController: NavController
) {
    var expanded by remember { mutableStateOf(false) }
    var optionName: String by remember { mutableStateOf(viewModel.optionsList[0]) }

    val lazyState = rememberLazyListState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = LightBlue)
                    .height(86.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(imageVector = Icons.Outlined.ArrowBackIosNew , contentDescription ="Back" )
                        }
                        Spacer(Modifier.width(8.dp))
                        viewModel.userName.value.let {
                            Text(
                                text = it,
                                Modifier
                                    .padding(start = 2.dp, top = 9.dp).width(150.dp),
                                fontSize = 22.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(Modifier.width(100.dp))

                        IconButton(onClick = {
                            viewModel.deleteEmpleado(
                                viewModel.empleadoUid.value
                            )
                            navController.popBackStack()
                            navController.navigateUp()
                        }) {
                            Icon(imageVector = Icons.Outlined.Delete , contentDescription ="Eliminar ", tint = Color.Red)
                        }

                    }


                    viewModel.empleadoUid.let {
                        Text(
                            text = "ID: ${it.value}",
                            Modifier
                                .padding(start = 8.dp),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Row {
                        viewModel.empleadoGmail.let {
                            Text(
                                text = it.value,
                                Modifier
                                    .padding(start = 8.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
        ) {
            Column {
                Row() {
                    Text(
                        text = "Actividades",
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                }

                Row(
                    modifier = Modifier
                        .clickable {
                            expanded = !expanded
                        }
                        .padding(18.dp)
                    ,
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(text = optionName, fontWeight = FontWeight.Medium, fontSize = 17.sp)
                    Icon(imageVector = Icons.Filled.ArrowDropDown, "drop down menu")
                    DropdownMenu(
                        modifier = Modifier.size(130.dp),
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }) {

                        viewModel.optionsList.forEach { option ->
                            DropdownMenuItem(
                                modifier = Modifier.width(140.dp) ,
                                onClick = {
                                    viewModel.filterBy(option)
                                    optionName = option
                                    expanded = false
                                }
                            ) {
                                Text(option)
                            }
                        }
                    }
                }

                LazyColumn(
                    Modifier.padding(16.dp),
                    state = lazyState,
                ) {
                    items(viewModel.actividadesList) {
                        Spacer(modifier = Modifier.height(10.dp))
                        ActividadesAdminItem(it,
                            onDelete = {
                                viewModel.eliminarActividad(viewModel.empleadoUid.value, it.id!! )
                        } ,onEdit = {
                            navController.navigate(Screens.AdminEditActivity.passIds(
                                viewModel.empleadoUid.value, it.id!!
                            ))
                        })
                        Spacer(modifier = Modifier.height(20.dp))

                    }
                }
            }

            if (viewModel.state.value.isLoading == true) {
                CircularProgressIndicator()
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
                        }) {
                            Text(text = "Okey")
                        }
                    }
                ) {
                    Text(text = viewModel.state.value.errorMsg!!)
                }
            }


        }

    }
}
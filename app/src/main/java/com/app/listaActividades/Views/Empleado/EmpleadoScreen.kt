package com.app.listaActividades.Views.Empleado

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.listaActividades.Views.Empleado.components.ActividadesItem
import com.app.listaActividades.ui.LightBlue
import com.app.listaActividades.util.Screens

@Composable
fun EmpleadoScreen(
    navController: NavController,
    viewModel: EmpleadoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
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
                    .height(89.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        viewModel.userName.value.let {
                            Text(
                                text = "Hola $it",
                                Modifier
                                    .padding(start = 8.dp),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier =Modifier.width(200.dp))

                        IconButton(onClick = {
                            viewModel.logOut(navController)
                        }) {
                            Icon(imageVector = Icons.Default.Logout , contentDescription = "Logout")
                        }
                    }

                    viewModel.userUid.let {
                        Text(
                            text = "ID: $it",
                            Modifier
                                .padding(start = 8.dp),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Row {
                        viewModel.userGmail?.let {
                            Text(
                                text = it,
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
                        ActividadesItem(it){
                            navController.navigate(Screens.EmpleadoActividadesDetailScreen.passTaskId(it.id!!))
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                    }
                }
            }

            if (viewModel.state.value.isLoading == true) {
                CircularProgressIndicator()
            }

            if (viewModel.state.value.isError != null) {
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
                    Text(text = viewModel.state.value.isError!!)
                }
            }


        }

    }

}
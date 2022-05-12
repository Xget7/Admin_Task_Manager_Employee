package com.app.listaActividades.Views.Empleado.EmpleadoActividadesDetail

import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.listaActividades.model.Actividad
import com.app.listaActividades.ui.ListaDeActividades

@Composable
fun EmpleadoActividadDetailScreen(
    viewModel: EmpleadoActividadDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(18.dp),
    ){
        LazyColumn() {
            item {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(imageVector = Icons.Outlined.ArrowBackIosNew , contentDescription ="Back" )
                }
                Spacer(modifier = Modifier.height(1.dp))
                Row() {
                    viewModel.currentActividad.value.activityTitle?.let {
                        Text(
                            text = it,
                            Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            fontSize = 32.sp,
                            textDecoration = TextDecoration.Underline,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Divider()
                Spacer(modifier = Modifier.height(30.dp))
                Row() {
                    viewModel.currentActividad.value.activityDireccion?.let {
                        Text(
                            text = it,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Tipo: ${viewModel.currentActividad.value.activityType}",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))

                viewModel.currentActividad.value.activityTime?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                viewModel.currentActividad.value.activityDescription?.let {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = it,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Visible
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Estado: ${viewModel.currentActividad.value.state}",
                    fontSize = 16.sp,
                    color = if (viewModel.currentActividad.value.state == "Pendiente") Color(0xFFF0BD3D) else Color(0xFF70B312),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )


                Spacer(modifier =  Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = {
                        viewModel.updateActivity(navController)
                    },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF8ABB47),
                        ),
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(8.dp)
                            .height(45.dp),
                    ) {
                        Text(text = "Marcar como realizada", color = Color.White, fontSize = 15.sp)
                    }
                }
            }



        }
    }
}



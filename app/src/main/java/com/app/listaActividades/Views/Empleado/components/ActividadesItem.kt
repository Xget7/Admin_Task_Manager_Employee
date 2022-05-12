package com.app.listaActividades.Views.Empleado.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
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
import com.app.listaActividades.model.Actividad
import com.app.listaActividades.ui.ListaDeActividades
import kotlin.math.max

@Composable
fun ActividadesItem(
    actividad : Actividad,
    onClick : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onClick() },
        elevation = 6.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White
    ){
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                actividad.activityTitle?.let {
                    Text(
                        text = it,
                        Modifier.padding(start = 8.dp , top = 8.dp, end = 8.dp).width(180.dp),
                        fontSize = 16.sp,
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                    )
                }

                Spacer(modifier = Modifier.width(100.dp))

                IconButton(onClick = { onClick() }) {
                    Icon(imageVector = Icons.Default.ArrowRight , contentDescription = "Navigate to Details" )
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                actividad.activityTime?.let {
                    Text(
                        text = "Horario de visita: $it",
                        Modifier
                            .padding(start = 8.dp),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            actividad.activityClient?.let {
                Text(
                    text = "Cliente: $it",
                    Modifier
                        .padding(start = 8.dp),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                )
            }

            actividad.activityDireccion?.let {
                Text(
                    text = "Direccion: $it",
                    Modifier
                        .padding(start = 8.dp),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                )
            }



            actividad.state?.let {
                Text(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth(),
                    text = it,
                    color = if (actividad.state == "Pendiente") Color(0xFFF0BD3D) else Color(0xFF70B312),
                    maxLines = 1,
                    fontSize = 16.sp,
                    textAlign = TextAlign.End,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}

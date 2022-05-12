package com.app.listaActividades.Views.Administrador.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
fun ActividadesAdminItem(
    actividad : Actividad,
    onEdit : () -> Unit,
    onDelete : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
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
                        Modifier
                            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                            .width(140.dp),
                        fontSize = 16.sp,
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                    )
                }

                Spacer(modifier = Modifier.width(100.dp))

                IconButton(onClick = { onEdit() }) {
                    Icon(imageVector = Icons.Default.Edit , contentDescription = "Edit Acitivty", tint = Color.Blue)
                }
                IconButton(onClick = { onDelete() }) {
                    Icon(imageVector = Icons.Default.RemoveCircleOutline , contentDescription = "Delete Acitivty", tint = Color.Red)
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Cliente: ${actividad.activityClient}",
                    Modifier
                        .padding(start = 8.dp)
                        .width(205.dp),
                    fontSize = 14.sp,
                    maxLines = 1,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.width(0.dp))

                actividad.activityTime?.let {
                    Text(
                        text = "Horario: $it",
                        Modifier
                            .padding(start = 0.dp).width(100.dp),
                        fontSize = 14.sp,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Text(
                text = "Direccion: ${actividad.activityDireccion}",
                Modifier
                    .padding(start = 8.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
            actividad.activityDescription?.let {
                Text(
                    modifier = Modifier
                        .height(70.dp)
                        .padding(8.dp),
                    text = it,
                    maxLines = 4,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth())
            {
                actividad.state?.let {
                    Text(
                        modifier = Modifier
                            .padding(14.dp),
                        text = it,
                        color = if (actividad.state == "Pendiente") Color(0xFFF0BD3D) else Color(0xFF70B312),
                        maxLines = 1,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                actividad.activityFinishedTime?.let {
                    Text(
                        modifier = Modifier
                            .padding(top= 14.dp)
                            .width(290.dp),
                        text = "Finalizo: ${actividad.activityFinishedTime}",
                        color =  Color(0xFF000000),
                        maxLines = 1,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }





            //Descripcion


        }
    }

}

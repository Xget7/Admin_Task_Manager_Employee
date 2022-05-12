package com.app.listaActividades.Views.Administrador.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.listaActividades.model.Empleado

@Composable
fun EmpleadosList(
    empleado : Empleado,
    onClick : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 26.dp, end = 26.dp)
            .clickable { onClick() },
        elevation = 6.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White
    ){
        Column(
            modifier = Modifier.padding(2.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = empleado.name,
                    Modifier.padding(start = 8.dp , top = 8.dp, end = 8.dp).width(180.dp),
                    fontSize = 16.sp,
                    maxLines = 1,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                )

                Spacer(modifier = Modifier.width(100.dp))

                IconButton(onClick = { onClick() }) {
                    Icon(imageVector = Icons.Default.ArrowRight , contentDescription = "Navigate to Details" )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = empleado.email,
                    Modifier
                        .padding(start = 8.dp),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )

            }

        }
    }
}
package com.app.listaActividades.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorPalette = lightColors(
    primary = Color(0xFFB5CDA3),
    primaryVariant = Color(0xFFC9E4C5),
    background = Color(0xFFFCF3ED),
    onBackground = Color(0xFF2E2E2E),
    surface = Color(0xFFDEFFEE),
    onSurface = Color(0xFF20577C),
    onSecondary = Color(0xFFC1AC95)
)

@Composable
fun ListaDeActividades(darkTheme: Boolean = true, content : @Composable() () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
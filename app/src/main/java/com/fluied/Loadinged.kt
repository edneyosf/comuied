package com.fluied

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fluied.util.Colors

private val colorDark = Colors.ACCENT_LIGHT
private val colorLight = Colors.ACCENT_DARK

@Composable
fun Loadinged(darkTheme: Boolean = isSystemInDarkTheme()){
    val color = if(darkTheme) colorDark else colorLight

    CircularProgressIndicator(color = color)
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

@Preview
@Composable
private fun LightMode() = LoadingedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = LoadingedPreview()

@Composable
private fun LoadingedPreview() = Previewed { Loadinged() }
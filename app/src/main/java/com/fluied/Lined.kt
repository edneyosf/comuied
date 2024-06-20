package com.fluied

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fluied.util.Colors

private val thicknessDefault = 1.dp

private val colorDark = Colors.DARK2
private val colorLight = Colors.LIGHT4

@Composable
fun Lined(thickness: Dp = thicknessDefault, darkTheme: Boolean = isSystemInDarkTheme()){
  val color = if(darkTheme) colorDark else colorLight

  Divider(color = color, thickness = thickness)
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

@Preview
@Composable
private fun LightMode() = LinedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = LinedPreview()

@Composable
private fun LinedPreview() = Previewed { Lined() }
package com.fluied

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fluied.util.Colors
import com.fluied.util.Icons

@Composable
fun Faithful(darkTheme: Boolean = isSystemInDarkTheme()){
    val colorFaithfulDark = Colors.ACCENT_LIGHT
    val colorFaithfulLight = Colors.ACCENT_DARK
    val color = if(darkTheme) colorFaithfulDark else colorFaithfulLight
    val icon = Icons.faithful()
    val modifier = Modifier
        .size(16.dp)

    Icon(
        modifier = modifier,
        imageVector = icon,
        tint = color,
        contentDescription = null)
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

@Preview
@Composable
private fun LightMode() = Faithful()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = Faithful()
package com.fluied.button

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fluied.Previewed
import com.fluied.util.Colors
import com.fluied.util.Icons

private val colorBackgroundDark = Colors.ACCENT_LIGHT
private val colorBackgroundLight = Colors.ACCENT_DARK
private val colorContentDark = Colors.DARK5
private val colorContentLight = Colors.LIGHT1

private val sizeIcon = 24.dp

@Composable
fun FloatingButtoned(icon: ImageVector, darkTheme: Boolean = isSystemInDarkTheme(),
                     onClick: () -> Unit){

    val modifier = Modifier.size(sizeIcon)
    val backgroundColor = if(darkTheme) colorBackgroundDark else colorBackgroundLight
    val contentColor = if (darkTheme) colorContentDark else colorContentLight

    FloatingActionButton(backgroundColor = backgroundColor, onClick = onClick) {
        Icon(
            modifier = modifier,
            imageVector = icon,
            tint = contentColor,
            contentDescription = null
        )
    }
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

@Preview
@Composable
private fun LightMode() = FloatingButtonedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = FloatingButtonedPreview()

@Composable
private fun FloatingButtonedPreview() = Previewed{ FloatingButtoned(icon = Icons.arrowBack()) { } }
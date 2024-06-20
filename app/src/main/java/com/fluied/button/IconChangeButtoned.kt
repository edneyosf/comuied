package com.fluied.button

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fluied.Previewed
import com.fluied.util.Colors

private val colorUnselectedDark = Colors.LIGHT5
private val colorUnselectedLight = Colors.DARK1
private val colorSelectedDark = Colors.ACCENT_LIGHT
private val colorSelectedLight = Colors.ACCENT_DARK
private val colorDisabledDark = Colors.LIGHT1.copy(alpha = 0.38f)
private val colorDisabledLight = Colors.DARK5.copy(alpha = 0.38f)

private val sizeIconDefault = 20.dp

@Composable
fun IconChangeButtoned(defaultIcon: ImageVector, activateIcon: ImageVector, enable: Boolean = true,
                       sizeIcon: Dp = sizeIconDefault, onChanged: (activate: Boolean) -> Unit,
                       darkTheme: Boolean = isSystemInDarkTheme()
){
    var activate by remember { mutableStateOf(false) }
    val icon = if(activate) activateIcon else defaultIcon
    val disabledColor = if(darkTheme) colorDisabledDark else colorDisabledLight
    val darkColor = if(activate) colorSelectedDark else colorUnselectedDark
    val lightColor = if(activate) colorSelectedLight else colorUnselectedLight
    val themeColor = if(darkTheme) darkColor else lightColor
    val color = if (enable) themeColor else disabledColor

    IconButton(
        enabled = enable,
        onClick = {
            activate = !activate
            onChanged(activate)
        }
    ) {
        Icon(
            imageVector = icon,
            tint = color,
            modifier = Modifier.size(sizeIcon),
            contentDescription = null
        )
    }
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private val iconDefaultPreview = Icons.Default.Add
private val iconActivatePreview = Icons.Default.Close

@Preview
@Composable
private fun LightMode() = IconChangeButtonedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = IconChangeButtonedPreview()

@Composable
private fun IconChangeButtonedPreview() = Previewed{
    IconChangeButtoned(
        defaultIcon = iconDefaultPreview,
        activateIcon = iconActivatePreview,
        onChanged = {})
    IconChangeButtoned(
        enable = false,
        defaultIcon = iconDefaultPreview,
        activateIcon = iconActivatePreview,
        onChanged = {})
}
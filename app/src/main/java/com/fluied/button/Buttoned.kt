package com.fluied.button

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.Previewed
import com.fluied.util.Colors

private val colorBackgroundDark = Colors.DARK3
private val colorBackgroundLight = Colors.LIGHT3
private val colorContentDark = Colors.LIGHT5
private val colorContentLight = Colors.DARK1
private val colorDisabledBackgroundDark = Colors.LIGHT1.copy(alpha = 0.12f)
private val colorDisabledBackgroundLight = Colors.DARK5.copy(alpha = 0.12f)
private val colorDisabledContentDark = Colors.LIGHT1.copy(alpha = 0.38f)
private val colorDisabledContentLight = Colors.DARK5.copy(alpha = 0.38f)

private val fontFamily = Fonts.MAIN
private val sizeTextFont = 16.sp

@Composable
fun Buttoned(text: String, icon: ImageVector? = null, enable: Boolean = true,
             onClick: () -> Unit, darkTheme: Boolean = isSystemInDarkTheme()){

    val backgroundEnabledColor = if(darkTheme) colorBackgroundDark else colorBackgroundLight
    val contentEnabledColor = if(darkTheme) colorContentDark else colorContentLight
    val backgroundDisabledColor =
        if(darkTheme) colorDisabledBackgroundDark else colorDisabledBackgroundLight
    val contentDisabledColor = if(darkTheme) colorDisabledContentDark else colorDisabledContentLight
    val backgroundColor = if(enable) backgroundEnabledColor else backgroundDisabledColor
    val contentColor = if(enable) contentEnabledColor else contentDisabledColor
    val elevation = ButtonDefaults
        .elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp)
    val colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = backgroundDisabledColor,
        disabledContentColor = contentDisabledColor)

    Button(elevation = elevation, enabled = enable, colors = colors, onClick = onClick) {
        if(icon != null){
            Iconed(icon = icon)
            Dividered()
        }
        Texted(text = text)
    }
}

@Composable
private fun Iconed(icon: ImageVector){
    val modifier = Modifier
        .size(ButtonDefaults.IconSize)

    Icon(
        imageVector = icon,
        modifier = modifier,
        contentDescription = null
    )
}

@Composable
private fun Dividered(){
    val modifier = Modifier
        .size(ButtonDefaults.IconSpacing)

    Spacer(modifier = modifier)
}

@Composable
private fun Texted(text: String){
    Text(
        text = text,
        fontSize = sizeTextFont,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold)
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private val iconPreview = Icons.Default.ArrowBack

private const val SAMPLE_TEXT = "Sample"

@Preview
@Composable
private fun LightMode() = ButtonPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = ButtonPreview()

@Composable
private fun ButtonPreview() = Previewed{
    Buttoned(text = SAMPLE_TEXT, onClick = {})
    Buttoned(text = SAMPLE_TEXT, icon = iconPreview, onClick = {})
    Buttoned(
        text = SAMPLE_TEXT,
        icon = iconPreview,
        enable = false,
        onClick = {}
    )
}
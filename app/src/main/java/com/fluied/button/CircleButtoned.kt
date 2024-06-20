package com.fluied.button

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.Previewed
import com.fluied.util.Colors
import com.fluied.util.Icons

private val colorBackgroundDark = Colors.DARK3
private val colorBackgroundLight = Colors.LIGHT3
private val colorContentDark = Colors.LIGHT5
private val colorContentLight = Colors.DARK1
private val colorSelectedBackgroundDark = Colors.ACCENT_LIGHT
private val colorSelectedBackgroundLight = Colors.ACCENT_DARK
private val colorSelectedContentDark = Colors.DARK5
private val colorSelectedContentLight = Colors.LIGHT1
private val colorDisabledBackgroundDark = Colors.LIGHT1.copy (alpha = 0.12f)
private val colorDisabledBackgroundLight = Colors.DARK5.copy(alpha = 0.12f)
private val colorDisabledContentDark = Colors.LIGHT1.copy(alpha = 0.38f)
private val colorDisabledContentLight = Colors.DARK5.copy(alpha = 0.38f)

private val fontFamily = Fonts.MAIN
private val sizeTextFont = 16.sp
private val sizeIcon = 18.dp

private val RippleRadius = 24.dp

@Composable
fun CircleButtoned(text: String? = null, icon: ImageVector? = null,
                   size: Dp? = null,
                   iconSize: Dp = sizeIcon, enabled: Boolean = true, selected: Boolean = false,
                   darkTheme: Boolean = isSystemInDarkTheme(), onClick: () -> Unit){

    val backgroundDarkColor = if(selected) colorSelectedBackgroundDark else colorBackgroundDark
    val backgroundLightColor = if(selected) colorSelectedBackgroundLight else colorBackgroundLight
    val backgroundEnabledColor = if(darkTheme) backgroundDarkColor else backgroundLightColor
    val contentDarkColor = if(selected) colorSelectedContentDark else colorContentDark
    val contentLightColor = if (selected) colorSelectedContentLight else colorContentLight
    val contentEnabledColor = if(darkTheme) contentDarkColor else contentLightColor
    val backgroundDisabledColor = if(darkTheme) colorDisabledBackgroundDark
        else colorDisabledBackgroundLight
    val contentDisabledColor = if(darkTheme) colorDisabledContentDark else colorDisabledContentLight
    val backgroundColor = if(enabled) backgroundEnabledColor else backgroundDisabledColor
    val contentColor = if(enabled) contentEnabledColor else contentDisabledColor
    val sizedModifier = Modifier
        .size(size ?: 0.dp)
        .clip(CircleShape)
        .background(backgroundColor, CircleShape)
        .clickable { onClick() }
    val defaultModifier = Modifier
        .background(backgroundColor, CircleShape)
        .minimumInteractiveComponentSize()
        .clickable(
            onClick = onClick,
            role = Role.Button,
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = false, radius = RippleRadius)
        )
    val modifier = if(size != null) sizedModifier else defaultModifier

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if(!text.isNullOrBlank()){
            Texted(text = text, color = contentColor)
        } else if(icon != null){
            Iconed(icon = icon, iconSize = iconSize,  color = contentColor)
        }
    }
}

@Composable
private fun Texted(text: String, color: Color){
    val modifier = Modifier
        .padding(top = 2.5.dp)

    Text(
        text = text,
        color = color,
        fontSize = sizeTextFont,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
private fun Iconed(icon: ImageVector, iconSize: Dp, color: Color){
    val modifier = Modifier
        .size(iconSize)

    Icon(
        imageVector = icon,
        tint = color,
        modifier = modifier,
        contentDescription = null
    )
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private const val SAMPLE_TEXT = "1"

@Preview
@Composable
private fun LightMode() = CircleButtonedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = CircleButtonedPreview()

@Composable
private fun CircleButtonedPreview() = Previewed{
    val sampleIcon = Icons.arrowBack()

    CircleButtoned(text = SAMPLE_TEXT, onClick = {})
    CircleButtoned(icon = sampleIcon, onClick = {})
    CircleButtoned(icon = sampleIcon, selected = true, onClick = {})
    CircleButtoned(text = SAMPLE_TEXT, enabled = false, onClick = {})
}
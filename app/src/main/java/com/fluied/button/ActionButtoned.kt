package com.fluied.button

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.Previewed
import com.fluied.util.Colors
import com.fluied.util.Icons

@Composable
fun ActionButtoned(text: String, icon: ImageVector? = null, enabled: Boolean = true,
                   darkTheme: Boolean = isSystemInDarkTheme(), onClick: () -> Unit){

    val colorBackgroundDark = Colors.ACCENT_LIGHT
    val colorBackgroundLight = Colors.ACCENT_DARK
    val colorContentDark = Colors.DARK5
    val colorContentLight = Colors.LIGHT1
    val alphaBackgroundDisabled = 0.12f
    val backgroundDisabledColorDark = Colors.LIGHT1.copy(alpha = alphaBackgroundDisabled)
    val backgroundDisabledColorLight = Colors.DARK5.copy(alpha = alphaBackgroundDisabled)
    val backgroundDisabledColor =
        if(darkTheme) backgroundDisabledColorDark else backgroundDisabledColorLight
    val alphaContentDisabled = 0.38f
    val contentDisabledColorDark = Colors.LIGHT1.copy(alpha = alphaContentDisabled)
    val contentDisabledColorLight = Colors.DARK5.copy(alpha = alphaContentDisabled)
    val contentDisabledColor = if(darkTheme) contentDisabledColorDark else contentDisabledColorLight
    val backgroundEnabledColor = if(darkTheme) colorBackgroundDark else colorBackgroundLight
    val backgroundColor = if (enabled) backgroundEnabledColor else backgroundDisabledColor
    val contentEnabledColor = if (darkTheme) colorContentDark else colorContentLight
    val contentColor = if(enabled) contentEnabledColor else contentDisabledColor
    val cornerSize = CornerSize(percent = 50)
    val shape = RoundedCornerShape(cornerSize)
    val alignment = Alignment.CenterVertically
    val arrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    val modifier = Modifier
        .fillMaxWidth()
        .clip(shape)
        .background(backgroundColor)
        .clickable { onClick() }
        .padding(vertical = 10.dp, horizontal = 16.dp)

    Row(
        modifier = modifier,
        horizontalArrangement = arrangement,
        verticalAlignment = alignment) {

        if(icon != null) Iconed(icon, tint = contentColor)
        Texted(text, color = contentColor)
    }
}

@Composable
private fun Iconed(icon: ImageVector, tint: Color){
    val modifier = Modifier
        .height(16.dp)

    Icon(
        modifier = modifier,
        tint = tint,
        imageVector = icon,
        contentDescription = null)
}

@Composable
private fun Texted(text: String, color: Color){
    val platform = PlatformTextStyle(includeFontPadding = false)
    val style = TextStyle(platformStyle = platform)

    Text(
        text = text.uppercase(),
        style = style,
        color = color,
        fontSize = 16.sp,
        fontFamily = Fonts.MAIN,
        fontWeight = FontWeight.Medium)
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

@Preview
@Composable
private fun WithIconLight() = WithIcon()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WithIconDark() = WithIcon()

@Preview
@Composable
private fun WithIconDisabledLight() = WithIconDisabled()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WithIconDisabledDark() = WithIconDisabled()

@Preview
@Composable
private fun NoIconLight() = ActionButtonedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NoIconDark() = ActionButtonedPreview()

@Preview
@Composable
private fun NoIconDisabledLight() = NoIconDisabled()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NoIconDisabledDark() = NoIconDisabled()

@Composable
private fun ActionButtonedPreview(enabled: Boolean = true, icon: ImageVector? = null) = Previewed {
    ActionButtoned(text = "Sample", icon, enabled, onClick = {})
}

@Composable
private fun WithIcon() = ActionButtonedPreview(icon = Icons.bookMark())

@Composable
private fun WithIconDisabled() = ActionButtonedPreview(enabled = false, icon = Icons.bookMark())

@Composable
private fun NoIconDisabled() = ActionButtonedPreview(enabled = false)
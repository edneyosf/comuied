package com.fluied

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fluied.util.Colors
import com.fluied.util.Icons

private val colorBackgroundDark = Colors.DARK3
private val colorBackgroundLight = Colors.LIGHT3
private val colorContentDark = Colors.LIGHT5
private val colorContentLight = Colors.DARK1

private val sizeButton = 44.dp
private val sizeIcon = 18.dp

private val paddingIcon = 8.dp
private val paddingStart = 25.dp
private val paddingEnd = 25.dp
private val paddingBottom = 30.dp

@Composable
fun Drivered(modifier: Modifier = Modifier, forwardDisabled: Boolean = false,
             nextDisabled: Boolean = false, darkTheme: Boolean = isSystemInDarkTheme(),
             onPrevious: () -> Unit, onNext: () -> Unit){

    val newModifier = modifier
        .fillMaxWidth()
        .padding(start = paddingStart, end = paddingEnd, bottom = paddingBottom)

    Box(modifier = newModifier){
        if(!forwardDisabled) ForwardArrowButton(darkTheme = darkTheme, onClick = onPrevious)
        if(!nextDisabled) NextArrowButton(darkTheme = darkTheme, onClick = onNext)
    }
}

@Composable
private fun BoxScope.ForwardArrowButton(darkTheme: Boolean, onClick: () -> Unit){
    ArrowButton(isNext = false, darkTheme = darkTheme, onClick = onClick)
}

@Composable
private fun BoxScope.NextArrowButton(darkTheme: Boolean, onClick: () -> Unit){
    ArrowButton(isNext = true, darkTheme = darkTheme, onClick = onClick)
}

@Composable
private fun BoxScope.ArrowButton(isNext: Boolean, darkTheme: Boolean, onClick: () -> Unit){
    val color = if(darkTheme) colorBackgroundDark else colorBackgroundLight
    val iconColor = if(darkTheme) colorContentDark else colorContentLight
    val buttonColor = ButtonDefaults.buttonColors(color)
    val align = if(isNext) Alignment.BottomEnd else Alignment.BottomStart
    val icon = if(isNext) Icons.arrowForward() else Icons.arrowBack()
    val elevation = ButtonDefaults.elevation(
        defaultElevation = 2.dp,
        pressedElevation = 4.dp,
        disabledElevation = 0.dp
    )

    Row(modifier = Modifier.align(align)){
        Button(
            colors = buttonColor,
            modifier= Modifier.size(sizeButton),
            elevation = elevation,
            shape = CircleShape,
            contentPadding = PaddingValues(paddingIcon),
            onClick = onClick){

            Iconed(icon = icon, tint = iconColor)
        }
    }
}

@Composable
private fun Iconed(icon: ImageVector, tint: Color){
    val modifier = Modifier
        .size(sizeIcon)

    Icon(icon, null,  modifier = modifier, tint = tint)
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

@Preview
@Composable
private fun LightMode() = DriveredPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = DriveredPreview()

@Composable
private fun DriveredPreview() = Previewed {
    Drivered(nextDisabled = true, onPrevious = {}, onNext = {})
    Drivered(forwardDisabled = true, onPrevious = {}, onNext = {})
    Drivered(onPrevious = {}, onNext = {})
}

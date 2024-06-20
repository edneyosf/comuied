package com.fluied.button

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

private val fontFamily = Fonts.MAIN
private val sizeTextFont = 16.sp

@Composable
fun SelectorButtoned(items: List<String>, onChanged: (index: Int) -> Unit,
                     darkTheme: Boolean = isSystemInDarkTheme()) {

    val backgroundColor = if(darkTheme) colorBackgroundDark else colorBackgroundLight
    val contentColor = if(darkTheme) colorContentDark else colorContentLight
    var index by remember { mutableStateOf(0) }

    Row {
        items.forEachIndexed { i, item ->
            val end = i != items.size - 1
            val current = i == index
            val selectedColor = if(current) backgroundColor else Color.Transparent
            val colors = ButtonDefaults
                .buttonColors(backgroundColor = selectedColor, contentColor = contentColor)

            Action(text = item, colors = colors, modifier = Modifier.weight(1f)) {
                index = i
                onChanged(index)
            }

            Dividered(end = end)
        }
    }
}

@Composable
private fun Action(text: String, colors: ButtonColors, modifier: Modifier, onClick: () -> Unit){
    val elevation = ButtonDefaults
        .elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp)

    Button(
        elevation = elevation,
        colors = colors,
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = sizeTextFont,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun Dividered(end: Boolean){
    val modifier = Modifier
        .width(20.dp)

    if(end) Spacer(modifier = modifier)
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private const val SAMPLE_TEXT = "Sample"
private val sampleItems = listOf(SAMPLE_TEXT, SAMPLE_TEXT)

@Preview
@Composable
private fun LightMode() = SelectorButtonedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = SelectorButtonedPreview()

@Composable
private fun SelectorButtonedPreview() = Previewed{
    SelectorButtoned(items = sampleItems, onChanged = {})
}
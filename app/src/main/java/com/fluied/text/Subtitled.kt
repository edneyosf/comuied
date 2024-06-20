package com.fluied.text

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.Previewed
import com.fluied.util.Colors

private val COLOR_DARK = Colors.LIGHT5
private val COLOR_LIGHT = Colors.DARK1

private val FONT_SIZE = 20.sp
private val FONT_FAMILY = Fonts.TITLE_BOOK
private val FONT_WEIGHT = FontWeight.SemiBold

private val PADDING = 20.dp

@Composable
fun Subtitled(text: String, darkTheme: Boolean = isSystemInDarkTheme()){
    val modifier = Modifier.padding(PADDING)
    val color = if(darkTheme) COLOR_DARK else COLOR_LIGHT

    Box(modifier = modifier) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            color = color,
            textAlign = TextAlign.Center,
            fontSize = FONT_SIZE,
            fontFamily = FONT_FAMILY,
            fontWeight = FONT_WEIGHT)
    }
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private const val SAMPLE_TEXT = "Sample"

@Preview
@Composable
private fun LightMode() = TitledPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = TitledPreview()

@Composable
private fun TitledPreview() = Previewed { Titled(text = SAMPLE_TEXT) }
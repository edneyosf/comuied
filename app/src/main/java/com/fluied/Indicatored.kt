package com.fluied

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.util.Colors

private val colorBackgroundDark = Colors.DARK3
private val colorBackgroundLight = Colors.LIGHT3
private val colorContentDark = Colors.LIGHT4
private val colorContentLight = Colors.DARK1

private val radius = 8.dp

@Composable
fun IndicatoredLeft(text: String, darkTheme: Boolean = isSystemInDarkTheme(),
                    onClick: (() -> Unit)? = null){

    Indicatored(
        text = text,
        faithful = false,
        position = Position.LEFT,
        darkTheme = darkTheme,
        onClick = onClick)
}

@Composable
fun IndicatoredRight(text: String, faithful: Boolean = false,
                     darkTheme: Boolean = isSystemInDarkTheme(), onClick: (() -> Unit)? = null){

    Indicatored(
        text = text,
        faithful = faithful,
        position = Position.RIGHT,
        darkTheme = darkTheme,
        onClick = onClick)
}

@Composable
private fun Indicatored(text: String, faithful: Boolean = false, position: Position,
                        onClick: (() -> Unit)? = null, darkTheme: Boolean = isSystemInDarkTheme()){

    val leftShape = RoundedCornerShape(topStart = radius, bottomStart = radius)
    val rightShape = RoundedCornerShape(topEnd = radius, bottomEnd = radius)
    val shape = if(position == Position.LEFT) leftShape else rightShape
    val fontFamily = if(position == Position.LEFT) Fonts.MAIN else Fonts.TITLE_BOOK
    val backgroundColor = if(darkTheme) colorBackgroundDark else colorBackgroundLight
    val contentColor = if(darkTheme) colorContentDark else colorContentLight
    val horizontalPadding = 15.dp
    val faithfulEnabled = faithful && position == Position.RIGHT
    val endFaithfulPadding = if(faithfulEnabled) 10.dp else horizontalPadding
    var modifier = Modifier
        .clip(shape)
        .background(color = backgroundColor)

    if(onClick != null) modifier = modifier.clickable { onClick() }

    modifier = modifier.padding(vertical = if(position == Position.LEFT) 0.dp else 2.5.dp)

    Box(modifier = modifier){
        Contented{
            Row(verticalAlignment = Alignment.CenterVertically){

                Spacer(modifier = Modifier.width(horizontalPadding))

                Texted(text = text, fontFamily = fontFamily, color = contentColor)

                if(faithfulEnabled) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Faithful()
                }

                Spacer(modifier = Modifier.width(endFaithfulPadding))
            }
        }
    }
}

@Composable
private fun Contented(content: @Composable BoxScope.() -> Unit){
    val verticalPadding = 5.dp
    val modifier = Modifier
        .padding(vertical = verticalPadding)

    Box(modifier = modifier, content = content)
}

@Composable
private fun Texted(text: String, fontFamily: FontFamily, color: Color){
    val sizeTextFont = 16.sp
    val modifier = Modifier
        .padding(top = (2.5).dp)

    Text(
        text,
        color = color,
        fontFamily = fontFamily,
        fontSize = sizeTextFont,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private const val SAMPLE_TEXT = "Sample"

@Preview
@Composable
private fun LightMode() = IndicatoredPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = IndicatoredPreview()

@Composable
private fun IndicatoredPreview() = Previewed {
    IndicatoredLeft(text = SAMPLE_TEXT, onClick = {})
    IndicatoredRight(text = SAMPLE_TEXT, onClick = {})
    IndicatoredRight(text = SAMPLE_TEXT, faithful = true, onClick = {})
}

private enum class Position{ LEFT, RIGHT }
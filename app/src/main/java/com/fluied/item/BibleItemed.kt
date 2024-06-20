package com.fluied.item

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.Faithful
import com.fluied.Previewed
import com.fluied.util.Colors
import com.fluied.util.toDp

private val colorAbbreviationDark = Colors.LIGHT1
private val colorAbbreviationLight = Colors.DARK5
private val colorMarkerDark = Colors.ACCENT_LIGHT
private val colorMarkerLight = Colors.ACCENT_DARK
private val colorVersionDark = Colors.LIGHT1.copy(alpha = 0.7f)
private val colorVersionLight = Colors.DARK5.copy(alpha = 0.7f)

private val fontFamily = Fonts.MAIN
private val fontFamilyAbbreviation = Fonts.TITLE_BOOK
private val sizeAbbreviationFont = 16.sp
private val sizeVersionFont = 14.5.sp

private val paddingHorizontal = 15.dp
private val paddingVertical = 15.dp

private val widthMarker = 4.dp
private val roundedCornerMarker = 12.dp

@Composable
fun BibleItemed(name: String, abbreviation: String, faithful: Boolean = false,
                selected: Boolean = false, darkTheme: Boolean = isSystemInDarkTheme(),
                onClick: () -> Unit) {

    val height = remember { mutableStateOf(IntSize(0, 0)) }
    val abbreviationColor = if(darkTheme) colorAbbreviationDark else colorAbbreviationLight
    val markerColor = if(darkTheme) colorMarkerDark else colorMarkerLight
    val versionColor = if (darkTheme) colorVersionDark else colorVersionLight
    val modifier = Modifier
        .fillMaxWidth()
        .defaultMinSize()
        .clickable(onClick = onClick)
        .onSizeChanged { height.value = it }

    val contentModifier = Modifier
        .padding(horizontal = paddingHorizontal, vertical = paddingVertical)

    Row(modifier = modifier){

        Marker(
            right = false,
            height = height.value.height,
            color = markerColor,
            selected = selected)

        Row(modifier = contentModifier.weight(1f), verticalAlignment = Alignment.CenterVertically){

            Content(
                abbreviation = { Abbreviation(text = abbreviation, textColor = abbreviationColor) },
                title = name,
                titleColor = versionColor,
                selected = selected,
                faithful = faithful)
        }

        Marker(
            right = true,
            height = height.value.height,
            color = markerColor,
            selected = selected)
    }
}

@Composable
private fun Marker(right: Boolean, height: Int, color: Color, selected: Boolean){
    val shapeRight = RoundedCornerShape(topEnd = roundedCornerMarker,
        bottomEnd = roundedCornerMarker)
    val shapeLeft = RoundedCornerShape(topStart = roundedCornerMarker,
        bottomStart = roundedCornerMarker)
    val shape = if(right) shapeRight else shapeLeft
    val modifier = Modifier
        .width(widthMarker)
        .height(height.toDp())
        .background(color = color, shape = shape)

    if(selected) Box(modifier = modifier)
}

@Composable
private fun Abbreviation(text: String, textColor: Color){
    val modifier = Modifier
        .padding(top = 2.5.dp)

    Text(
        modifier = modifier,
        text = text,
        color = textColor,
        textAlign = TextAlign.Center,
        fontFamily = fontFamilyAbbreviation,
        fontWeight = FontWeight.Bold,
        fontSize = sizeAbbreviationFont)
}

@Composable
private fun Content(faithful: Boolean, abbreviation: @Composable RowScope.() -> Unit, title: String,
                        titleColor: Color, selected: Boolean){

    val weight = if(selected) FontWeight.Bold else FontWeight.Normal

    Column{

        Text(
            text = title,
            color = titleColor,
            fontFamily = fontFamily,
            fontSize = sizeVersionFont,
            fontWeight = weight)

        Row(verticalAlignment = Alignment.CenterVertically){
            abbreviation()
            Spacer(modifier = Modifier.width(5.dp))
            if(faithful) Faithful()
        }
    }
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private const val SAMPLE_NAME = "Bible"
private const val SAMPLE_ABBREVIATION = "Bb"

@Preview
@Composable
private fun LightMode() = BookItemedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = BookItemedPreview()

@Composable
private fun BookItemedPreview() = Previewed {
    BibleItemed(
        name = SAMPLE_NAME,
        abbreviation = SAMPLE_ABBREVIATION,
        onClick = {})
    BibleItemed(
        name = SAMPLE_NAME,
        abbreviation = SAMPLE_ABBREVIATION,
        selected = true,
        onClick = {})
    BibleItemed(
        name = SAMPLE_NAME,
        abbreviation = SAMPLE_ABBREVIATION,
        selected = true,
        faithful = true,
        onClick = {})
}
package com.fluied.item

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.Previewed
import com.fluied.util.Colors

private val colorBackgroundAbbreviationDark = Colors.DARK4
private val colorBackgroundAbbreviationLight = Colors.LIGHT2
private val colorAbbreviationDark = Colors.ACCENT_LIGHT
private val colorAbbreviationLight = Colors.ACCENT_DARK
private val colorMarkerDark = Colors.ACCENT_LIGHT
private val colorMarkerLight = Colors.ACCENT_DARK
private val colorBookNameDark = Colors.LIGHT1
private val colorBookNameLight = Colors.DARK5
private val colorOriginalBookNameDark = Colors.LIGHT1.copy(alpha = 0.4f)
private val colorOriginalBookNameLight = Colors.DARK5.copy(alpha = 0.4f)

private val fontFamily = Fonts.MAIN
private val fontFamilyAbbreviation = Fonts.TITLE_BOOK
private val fontFamilyHebrew = Fonts.FOREIGNER
private val sizeAbbreviationFont = 14.sp
private val sizeBookNameFont = 17.5.sp
private val sizeOriginalBookNameFont = 16.sp

private val paddingHorizontal = 15.dp
private val paddingVertical = 15.dp

private val widthMarker = 4.dp
private val heightMarker = 70.dp
private val roundedCornerMarker = 12.dp

@Composable
fun BookItemed(abbreviation: String, name: String, selected: Boolean = false,
               originalBookName: String, darkTheme: Boolean = isSystemInDarkTheme(),
               onClick: () -> Unit) {

    val backgroundAbbreviationColor = if (darkTheme) colorBackgroundAbbreviationDark
        else colorBackgroundAbbreviationLight
    val abbreviationColor = if(darkTheme) colorAbbreviationDark else colorAbbreviationLight
    val markerColor = if(darkTheme) colorMarkerDark else colorMarkerLight
    val bookNameColor = if (darkTheme) colorBookNameDark else colorBookNameLight
    val originalBookNameColor = if (darkTheme) colorOriginalBookNameDark
        else colorOriginalBookNameLight
    val modifier = Modifier
        .fillMaxWidth()
        .defaultMinSize()
        .clickable(onClick = onClick)
    val contentModifier = Modifier
        .padding(horizontal = paddingHorizontal, vertical = paddingVertical)

    Row(modifier = modifier){

        Marker(right = false, color = markerColor, selected = selected)

        Row(modifier = contentModifier.weight(1f), verticalAlignment = Alignment.CenterVertically){

            Abbreviation(
                text = abbreviation,
                textColor = abbreviationColor,
                backgroundColor = backgroundAbbreviationColor)

            TextContent(
                title = name,
                titleColor = bookNameColor,
                subtitle = originalBookName,
                subtitleColor = originalBookNameColor,
                selected = selected)
        }

        Marker(right = true, color = markerColor, selected = selected)
    }
}

@Composable
private fun Marker(right: Boolean, color: Color, selected: Boolean){
    val shapeRight = RoundedCornerShape(topEnd = roundedCornerMarker,
        bottomEnd = roundedCornerMarker)
    val shapeLeft = RoundedCornerShape(topStart = roundedCornerMarker,
        bottomStart = roundedCornerMarker)
    val shape = if(right) shapeRight else shapeLeft
    val modifier = Modifier
        .width(widthMarker)
        .height(heightMarker)
        .background(color = color, shape = shape)

    if(selected) Box(modifier = modifier)
}

@Composable
private fun Abbreviation(text: String, textColor: Color, backgroundColor: Color){
    val shape = RoundedCornerShape(6.dp)
    val modifier = Modifier
        .clip(shape = shape)
        .width(50.dp)
        .background(color = backgroundColor)
        .padding(vertical = 10.dp)

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            fontFamily = fontFamilyAbbreviation,
            fontWeight = FontWeight.Bold,
            fontSize = sizeAbbreviationFont)
    }
}

@Composable
private fun TextContent(title: String, titleColor: Color, subtitle: String, subtitleColor: Color,
                        selected: Boolean){

    val weight = if(selected) FontWeight.Bold else FontWeight.Normal
    val verticalArrangement = Arrangement.spacedBy((-5).dp)
    val modifier = Modifier
        .padding(start = 15.dp)

    Column(modifier = modifier, verticalArrangement = verticalArrangement) {

        Text(
            text = title,
            color = titleColor,
            fontFamily = fontFamily,
            fontSize = sizeBookNameFont,
            fontWeight = weight)

        Text(
            text = subtitle,
            color = subtitleColor,
            fontFamily = fontFamilyHebrew,
            fontSize = sizeOriginalBookNameFont)
    }
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private const val SAMPLE_NAME = "Book"
private const val SAMPLE_ORIGINAL_NAME = "סֵפֶר"
private const val SAMPLE_ABBREVIATION = "Bk"

@Preview
@Composable
private fun LightMode() = BookItemedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = BookItemedPreview()

@Composable
private fun BookItemedPreview() = Previewed {
    BookItemed(
        name = SAMPLE_NAME,
        originalBookName = SAMPLE_ORIGINAL_NAME,
        abbreviation = SAMPLE_ABBREVIATION,
        onClick = {})
    BookItemed(
        name = SAMPLE_NAME,
        originalBookName = SAMPLE_ORIGINAL_NAME,
        abbreviation = SAMPLE_ABBREVIATION,
        selected = true,
        onClick = {})
}
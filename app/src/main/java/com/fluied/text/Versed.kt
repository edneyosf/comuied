package com.fluied.text

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.Previewed
import com.fluied.util.Colors
import com.fluied.util.Icons
import com.fluied.util.toDp
import com.fluied.util.verseWithStyle

private val HORIZONTAL_PADDING = 20.dp

@Composable
fun Versed(index: String, text: String, selected: Boolean = false, bookmark: Boolean = false,
           darkTheme: Boolean = isSystemInDarkTheme(), onClick: () -> Unit){

    val colorSelected = if (darkTheme) Colors.SELECTED_DARK else Colors.SELECTED_LIGHT
    val colorBackground = if(selected) colorSelected else Colors.TRANSPARENT
    val verticalPadding = 5.dp
    val modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .background(color = colorBackground)
        .padding(start = HORIZONTAL_PADDING, top = verticalPadding, bottom = verticalPadding)
    val arrangement = Arrangement.spacedBy(space = 10.dp)
    val alignment = Alignment.Bottom
    val textSize = remember { mutableStateOf(IntSize(0, 0)) }

    Row(
        modifier = modifier,
        horizontalArrangement = arrangement,
        verticalAlignment = alignment) {

        Verse(index, text, darkTheme, textSize)
        BookMark(enabled = bookmark, darkTheme, textSize.value)
    }
}

@Composable
private fun RowScope.Verse(index: String, text: String, darkTheme: Boolean,
                           textSize: MutableState<IntSize>){

    val colorIndexDark = Colors.LIGHT5
    val colorIndexLight = Colors.DARK1
    val colorTextDark = Colors.WHITE
    val colorTextLight = Colors.BLACK
    val colorIndex = if(darkTheme) colorIndexDark else colorIndexLight
    val colorText = if(darkTheme) colorTextDark else colorTextLight
    val baseLineShiftIndex = BaselineShift(0.1f)
    val styleIndex = SpanStyle(
        baselineShift = baseLineShiftIndex,
        color = colorIndex,
        fontSize = 16.5.sp,
        fontFamily = Fonts.TEXT_BOOK,
        fontWeight = FontWeight.Bold)
    val styleText = SpanStyle(
        color = colorText,
        fontSize = 20.sp,
        fontFamily = Fonts.TEXT_BOOK,
        fontWeight = FontWeight.Normal)
    val modifier = Modifier
        .weight(1f)
        .onSizeChanged { textSize.value = it }
    val content = buildAnnotatedString {
        withStyle(style = styleIndex) { append(index) }
        withStyle(style = styleText) { append("  ") }
        verseWithStyle(text, styleText)
    }

    Text(modifier = modifier, text = content)
}

@Composable
private fun BookMark(enabled: Boolean, darkTheme: Boolean, textSize: IntSize){
    if(enabled){
        val darkColor = Colors.ACCENT_LIGHT
        val lightColor = Colors.ACCENT_DARK
        val color = if(darkTheme) darkColor else lightColor
        val iconSize = remember { mutableStateOf(IntSize(0, 0)) }
        val brushColors = listOf(Colors.TRANSPARENT, color)
        val paintBrush = Brush.verticalGradient(colors = brushColors)
        val paintHeight = textSize.height.toDp() - iconSize.value.height.toDp()
        val paintWidth = iconSize.value.width.toDp()
        val paintModifier = Modifier
            .height(paintHeight)
            .width(paintWidth)
            .background(brush = paintBrush)
        val iconModifier = Modifier
            .height(20.dp)
            .onSizeChanged { iconSize.value = it }

        Row {
            Column {
                Box(modifier = paintModifier)
                Icon(
                    modifier = iconModifier,
                    imageVector = Icons.bookMarkLong(),
                    tint = color,
                    contentDescription = null)
            }
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
    else Spacer(modifier = Modifier.width(10.dp))
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

@Preview
@Composable
private fun LightMode() = VersedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = VersedPreview()

@Preview
@Composable
private fun SelectedLight() = SelectedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SelectedDark() = SelectedPreview()

@Preview
@Composable
private fun WithBookMarkLight() = WithBookMarkPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WithBookMarkDark() = WithBookMarkPreview()

@Preview
@Composable
private fun SelectedWithBookMarkLight() = SelectedWithBookMarkPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SelectedWithBookMarkDark() = SelectedWithBookMarkPreview()

@Composable
private fun VersedPreview(selected: Boolean = false, bookmark: Boolean = false) = Previewed {
    val index = "1"
    val text = "Mussum Ipsum, cacilds vidis litro abertis. Cevadis im ampola pa arma uma " +
            "pindureta. Copo furadis é disculpa de bebadis, arcu quam euismod magna. Todo mundo " +
            "vê os porris que eu tomo, mas ninguém vê os tombis que eu levo! Atirei o pau no " +
            "gatis, per gatis num morreus."

    Versed(index, text, selected, bookmark, onClick = {})
}

@Composable
private fun WithBookMarkPreview() = VersedPreview(bookmark = true)

@Composable
private fun SelectedPreview() = VersedPreview(selected = true)

@Composable
private fun SelectedWithBookMarkPreview() = VersedPreview(selected = true, bookmark = true)
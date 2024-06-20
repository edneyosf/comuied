package com.fluied.button

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.Previewed
import com.fluied.R
import com.fluied.util.Colors
import com.fluied.util.Icons

private val sizeIcon = 24.dp

private val idMarkerText = R.string.bookmark_marker
private val idContinueText = R.string.bookmark_continue
private val fontFamily = Fonts.MAIN
private val sizeTitleFont = 14.sp
private val sizeTextFont = 16.sp
private val sizeActionFont = 12.sp

private val colorIconDark = Colors.ACCENT_LIGHT
private val colorIconLight = Colors.ACCENT_DARK
private val colorBackgroundDark = Colors.DARK3
private val colorBackgroundLight = Colors.LIGHT3
private val colorTitleDark = Colors.LIGHT1
private val colorTitleLight = Colors.DARK5
private val colorSubTitleDark = Colors.LIGHT5
private val colorSubTitleLight = Colors.DARK1

private val radius = 8.dp

@Composable
fun BookMarkButtoned(bookAbbreviation: String, chapterIndex: Int, verseIndex: Int,
                     enabled: Boolean = true, onClick: () -> Unit,
                     darkTheme: Boolean = isSystemInDarkTheme()){

    val title = stringResource(id = idMarkerText)
    val text = "$bookAbbreviation $chapterIndex:$verseIndex"
    val icon = Icons.bookMark()
    val iconColor = if(darkTheme) colorIconDark else colorIconLight
    val titleColor = if (darkTheme) colorTitleDark else colorTitleLight
    val subTitleColor = if (darkTheme) colorSubTitleDark else colorSubTitleLight
    val shape = RoundedCornerShape(radius)
    val padding = PaddingValues(start = 10.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    val backgroundColor = ButtonDefaults
        .buttonColors(backgroundColor = if(darkTheme) colorBackgroundDark else colorBackgroundLight)
    val elevation = ButtonDefaults
        .elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp)
    val modifier = Modifier
        .fillMaxWidth()

    Button(
        colors = backgroundColor,
        elevation = elevation,
        shape = shape,
        enabled = enabled,
        modifier = modifier,
        contentPadding = padding,
        onClick = onClick){

       Contented{

           Iconed(icon = icon, color = iconColor)

           TextContent(
               title = title,
               titleColor = subTitleColor,
               text = text,
               textColor = titleColor)

           Spacer(Modifier.weight(1f))

           TextAction(color = subTitleColor)
       }
    }
}

@Composable
private fun Contented(content: @Composable RowScope.() -> Unit){
    val verticalAlignment = Alignment.CenterVertically
    val modifier = Modifier
        .padding(vertical = 5.dp, horizontal = 10.dp)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = verticalAlignment,
        content = content)
}

@Composable
private fun Iconed(icon: ImageVector, color: Color){
    val modifier = Modifier
        .size(sizeIcon)

    Icon(
        imageVector = icon,
        modifier = modifier,
        tint = color,
        contentDescription = null)
}

@Composable
private fun TextContent(title: String, titleColor: Color, text: String, textColor: Color){
    val spacedBy = (-2).dp
    val verticalArrangement = Arrangement.spacedBy(spacedBy, Alignment.CenterVertically)
    val modifier = Modifier
        .padding(start = 15.dp, top = 4.dp)

    Column(modifier = modifier, verticalArrangement = verticalArrangement) {

        Text(
            text = title,
            color = titleColor,
            fontSize = sizeTitleFont,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal)

        Text(
            text = text,
            color = textColor,
            fontSize = sizeTextFont,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun TextAction(color: Color){
    val text = stringResource(idContinueText)

    Text(
        text = text,
        color = color,
        fontSize = sizeActionFont,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold)
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private const val BOOK_NAME_SAMPLE = "1Sp"
private const val CHAPTER_INDEX_SAMPLE = 111
private const val VERSE_INDEX_SAMPLE = 111

@Preview
@Composable
private fun LightMode() = BookMarkButtonedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = BookMarkButtonedPreview()

@Composable
private fun BookMarkButtonedPreview() = Previewed{
    BookMarkButtoned(
        bookAbbreviation = BOOK_NAME_SAMPLE,
        chapterIndex = CHAPTER_INDEX_SAMPLE,
        verseIndex = VERSE_INDEX_SAMPLE,
        onClick = {})
}
package com.fluied.button

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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

private val idResumeText = R.string.resume_reading_continue

private val sizeLabelFont = 14.sp
private val sizeTextFont = 16.sp

private val sizeIcon = 20.dp

private val colorBackgroundDark = Colors.ACCENT_LIGHT
private val colorBackgroundLight = Colors.ACCENT_DARK
private val colorContentDark = Colors.DARK5
private val colorContentLight = Colors.LIGHT1

@Composable
fun ResumeReadingButtoned(bookAbbreviation: String, chapterIndex: Int, verseIndex: Int,
                          darkTheme: Boolean = isSystemInDarkTheme(), onClick: () -> Unit){

    val label = stringResource(id = idResumeText)
    val text = "$bookAbbreviation $chapterIndex:$verseIndex"
    val icon = Icons.resume()
    val backgroundColor = if(darkTheme) colorBackgroundDark else colorBackgroundLight
    val contentColor = if (darkTheme) colorContentDark else colorContentLight
    val elevation = FloatingActionButtonDefaults.elevation()
    val cornerSize = CornerSize(percent = 50)
    val shape = MaterialTheme.shapes.small.copy(cornerSize)

    FloatingActionButton(
        onClick = onClick,
        shape = shape,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation
    ) {
        Contented{
            Iconed(icon, tint = contentColor)
            TextContent(label, text, color = contentColor)
        }
    }
}

@Composable
private fun Contented (content: @Composable RowScope.() -> Unit){
    val alignment = Alignment.CenterVertically
    val arrangement = Arrangement.spacedBy(10.dp)
    val modifier = Modifier
        .padding(start = 15.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)

    Row(
        modifier = modifier,
        horizontalArrangement = arrangement,
        verticalAlignment = alignment,
        content = content)
}

@Composable
private fun Iconed(icon: ImageVector, tint: Color){
    val modifier = Modifier.size(sizeIcon)

    Icon(
        modifier = modifier,
        tint = tint,
        imageVector = icon,
        contentDescription = null)
}

@Composable
private fun TextContent(label: String, text: String, color: Color){
    val spacedBy = (-5).dp
    val alignment = Arrangement.spacedBy(spacedBy)
    val colorLabel = color.copy(alpha = 0.8f)

    Column(verticalArrangement = alignment) {

        Text(
            text = label,
            color = colorLabel,
            fontSize = sizeLabelFont,
            fontFamily = Fonts.MAIN,
            fontWeight = FontWeight.Normal)

        Text(
            text = text,
            color = color,
            fontSize = sizeTextFont,
            fontFamily = Fonts.MAIN,
            fontWeight = FontWeight.Medium)
    }
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private const val BOOK_NAME_SAMPLE = "1Sp"
private const val CHAPTER_INDEX_SAMPLE = 111
private const val VERSE_INDEX_SAMPLE = 111

@Preview
@Composable
private fun LightMode() = ResumeReadingButtonedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = ResumeReadingButtonedPreview()

@Composable
private fun ResumeReadingButtonedPreview() = Previewed {
    ResumeReadingButtoned(
        bookAbbreviation = BOOK_NAME_SAMPLE,
        chapterIndex = CHAPTER_INDEX_SAMPLE,
        verseIndex = VERSE_INDEX_SAMPLE, onClick = {})
}
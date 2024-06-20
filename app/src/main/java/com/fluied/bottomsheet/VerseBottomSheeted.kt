package com.fluied.bottomsheet

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.Previewed
import com.fluied.R
import com.fluied.button.ActionButtoned
import com.fluied.util.Colors
import com.fluied.util.Icons

@Composable
fun VerseBottomSheeted(title: String, enabledBookMark: Boolean = true, onBookMark: () -> Unit,
                       darkTheme: Boolean = isSystemInDarkTheme()){

    val backgroundColorDark = Colors.DARK3
    val backgroundColorLight = Colors.WHITE
    val backgroundColor = if(darkTheme) backgroundColorDark else backgroundColorLight
    val arrangement = Arrangement.spacedBy(15.dp)
    val modifier = Modifier
        .background(color = backgroundColor)
        .padding(top = 20.dp, bottom = 40.dp)
        .fillMaxWidth()

    Column(modifier, verticalArrangement = arrangement) {
        Decoration(darkTheme)
        Title(text = title, darkTheme)
        Content(enabledBookMark, onBookMark)
    }
}

@Composable
private fun ColumnScope.Decoration(darkTheme: Boolean){
    val darkColor = Colors.WHITE.copy(alpha = 0.05f)
    val lightColor = Colors.BLACK.copy(alpha = 0.05f)
    val color = if(darkTheme) darkColor else lightColor
    val shape = RoundedCornerShape(50)
    val align = Alignment.CenterHorizontally
    val modifier = Modifier
        .clip(shape)
        .background(color)
        .height(5.dp)
        .width(50.dp)
        .align(align)

    Box(modifier)
}

@Composable
private fun Title(text: String, darkTheme: Boolean){
    val darkColor = Colors.LIGHT1
    val lightColor = Colors.DARK5
    val color = if(darkTheme) darkColor else lightColor
    val modifier = Modifier
        .padding(horizontal = 20.dp)

    Box(modifier){
        Text(text, fontFamily = Fonts.MAIN, color = color, fontSize = 18.sp)
    }
}

@Composable
private fun Content(enabledBookMark: Boolean = true, onBookMark: () -> Unit){
    val modifier = Modifier
        .padding(horizontal = 20.dp)

    Column(modifier){
        BookMark(enabled = enabledBookMark, onBookMark)
    }
}

@Composable
private fun BookMark(enabled: Boolean = true, onBookMark: () -> Unit){
    val textEnabled = stringResource(id = R.string.verse_bottom_sheet_mark)
    val textDisabled = stringResource(id = R.string.verse_bottom_sheet_marked)
    val text = if(enabled) textEnabled else textDisabled

    ActionButtoned(text, icon = Icons.bookMark(), enabled, onClick = { if(enabled) onBookMark() })
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

@Preview
@Composable
private fun LightMode() = VerseBottomSheetedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = VerseBottomSheetedPreview()

@Preview
@Composable
private fun BookMarkDisabledLight() = BookMarkDisabled()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BookMarkDisabledDark() = BookMarkDisabled()

@Composable
private fun VerseBottomSheetedPreview(enabledBookMark: Boolean = true){
    Previewed(transparent = true) {
        VerseBottomSheeted(title = "1Sample 111:111", enabledBookMark, onBookMark = {})
    }
}

@Composable
private fun BookMarkDisabled() = VerseBottomSheetedPreview(enabledBookMark = false)
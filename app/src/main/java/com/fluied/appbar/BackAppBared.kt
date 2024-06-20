package com.fluied.appbar

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.button.CircleButtoned
import com.fluied.util.Colors
import com.fluied.util.Icons

private val colorTitleDark = Colors.LIGHT1
private val colorTitleLight = Colors.DARK5

private val fontFamily = Fonts.MAIN
private val sizeTitleFont = 18.sp

@Composable
fun BackAppBar(title: String, onBackPressed: () -> Unit, darkTheme: Boolean = isSystemInDarkTheme()){
    val titleColor = if(darkTheme) colorTitleDark else colorTitleLight
    val icon = Icons.arrowBack()
    val verticalAlignment = Alignment.CenterVertically
    val modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)

    Row(modifier = modifier, verticalAlignment = verticalAlignment) {

        CircleButtoned(icon = icon, onClick = onBackPressed)

        Dividered()

        Text(text = title, color = titleColor, fontSize = sizeTitleFont, fontFamily = fontFamily)
    }
}

@Composable
private fun Dividered(){
    val modifier = Modifier
        .width(15.dp)

    Spacer(modifier = modifier)
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private val colorBackgroundPreviewDark = Colors.DARK5
private val colorBackgroundPreviewLight = Colors.LIGHT1

private const val TITLE_SAMPLE = "Sample"

@Preview
@Composable
private fun LightMode(){
    val modifier = Modifier
        .background(color = colorBackgroundPreviewLight)

    Box(modifier = modifier){
        BackAppBar(title = TITLE_SAMPLE, onBackPressed = {})
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode(){
    val modifier = Modifier
        .background(color = colorBackgroundPreviewDark)

    Box(modifier = modifier) {
        BackAppBar(title = TITLE_SAMPLE, onBackPressed = {})
    }
}

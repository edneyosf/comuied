package com.fluied

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fluied.util.Colors

private val colorBackgroundPreviewDark = Colors.DARK5
private val colorBackgroundPreviewLight = Colors.LIGHT1

private val paddingPreview = 25.dp
private val marginPreview = 10.dp

@Composable
internal fun Previewed(transparent: Boolean = false, content: @Composable ColumnScope.() -> Unit){
    val darkTheme = isSystemInDarkTheme()
    val backgroundColor = if(darkTheme) colorBackgroundPreviewDark else colorBackgroundPreviewLight
    val arrangement = Arrangement.spacedBy(marginPreview)
    val modifier = Modifier
        .background(color = if(transparent) Colors.TRANSPARENT else backgroundColor)
        .padding(paddingPreview)

    Column(
        modifier = modifier,
        verticalArrangement = arrangement,
        content = content)
}
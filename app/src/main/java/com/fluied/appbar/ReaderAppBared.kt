package com.fluied.appbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fluied.IndicatoredLeft
import com.fluied.IndicatoredRight
import com.fluied.Previewed
import com.fluied.button.CircleButtoned
import com.fluied.util.Icons

private val PADDING_VERTICAL = 4.dp
private val PADDING_HORIZONTAL = 15.dp
private val MARGIN = 2.dp
private val SIZE_CLOSE_ACTION = 38.dp
private val SIZE_CLOSE_ICON = 16.dp

@Composable
fun ReaderAppBared(nameBook: String, indexChapter: Int, nameBible: String, onTitle: () -> Unit,
                   onBibleVersion: () -> Unit, onClose: () -> Unit){

    val icon = Icons.close()
    val modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = PADDING_VERTICAL, horizontal = PADDING_HORIZONTAL)

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {

        Title(nameBook = nameBook, indexChapter = indexChapter, onClick = onTitle)

        Divider()

        BibleVersion(nameBible = nameBible, onClick = onBibleVersion)

        Spacer(modifier = modifier.weight(1f))

        CircleButtoned(size = SIZE_CLOSE_ACTION, iconSize = SIZE_CLOSE_ICON, icon = icon) {
            onClose()
        }
    }
}

@Composable
private fun Title(nameBook: String, indexChapter: Int, onClick: () -> Unit){
    val text = "$nameBook $indexChapter"

    IndicatoredLeft(text = text, onClick = onClick)
}

@Composable
private fun BibleVersion(nameBible: String, onClick: () -> Unit){
    IndicatoredRight(text = nameBible, onClick = onClick, faithful = nameBible == "ACF" || nameBible == "KJV")
}

@Composable
private fun Divider(){
    val modifier = Modifier.width(MARGIN)

    Spacer(modifier = modifier)
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private const val SAMPLE_BOOK_NAME = "Sample"
private const val SAMPLE_INDEX_CHAPTER = 1
private const val SAMPLE_NAME_BIBLE = "SP"

@Preview
@Composable
private fun LightMode() = ReaderAppBared()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = ReaderAppBared()

@Composable
private fun ReaderAppBared() = Previewed{
    ReaderAppBared(
        nameBook = SAMPLE_BOOK_NAME,
        indexChapter = SAMPLE_INDEX_CHAPTER,
        nameBible = SAMPLE_NAME_BIBLE,
        onTitle = {},
        onBibleVersion = {},
        onClose = {}
    )
}
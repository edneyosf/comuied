package com.fluied.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun Int.toDp() = with(LocalDensity.current) { this@toDp.toDp() }

fun AnnotatedString.Builder.verseWithStyle(content: String, defaultStyle: SpanStyle) {
    val parts = content.split("<s><i>", "</i></s>")
    var scItalic = false

    for (part in parts) {
        if (scItalic){
            val style = defaultStyle.copy(fontStyle = FontStyle.Italic)
            val endStyle = style.copy(fontSize = 16.sp)
            val text = part.uppercase()

            withStyle(style) { append(text.first()) }
            withStyle(endStyle) { append(text.substring(1, text.length)) }
        }
        else {
            val parts1 = part.split("<i>", "</i>")
            var italic = false

            for (part1 in parts1){
                if (italic){
                    val style = defaultStyle.copy(fontStyle = FontStyle.Italic)

                    withStyle(style) { append(part1) }
                }
                else {
                    val parts2 = part1.split("<s>", "</s>")
                    var sc = false

                    for(part2 in parts2){
                        if(sc){
                            val endStyle = defaultStyle.copy(fontSize = 16.sp)
                            val text = part2.uppercase()

                            withStyle(defaultStyle) { append(text.first()) }
                            withStyle(endStyle) { append(text.substring(1, text.length)) }
                        }
                        else withStyle(defaultStyle) { append(part2) }

                        sc = !sc
                    }
                }
                italic = !italic
            }
        }
        scItalic = !scItalic
    }
}
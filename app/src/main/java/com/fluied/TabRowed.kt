package com.fluied

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fluied.util.Colors

private val colorUnselectedDark = Colors.LIGHT5
private val colorUnselectedLight = Colors.DARK1
private val colorSelectedDark = Colors.ACCENT_LIGHT
private val colorSelectedLight = Colors.ACCENT_DARK
private val colorRippleDark = Colors.LIGHT1.copy(alpha = 0.38f)
private val colorRippleLight = Colors.DARK5.copy(alpha = 0.38f)

private val fontFamily = Fonts.MAIN
private val sizeTextFont = 14.sp

private const val durationIndicatorAnimation = 250
private val easingIndicatorAnimation = FastOutSlowInEasing
private val marginIndicator = 10.dp
private val heightIndicator = 3.dp
private val radiusIndicator = 12.dp

@Composable
fun TabRowed(indexSelected: Int, items: List<String>, darkTheme: Boolean = isSystemInDarkTheme(),
             onChanged: (index: Int) -> Unit) {

    val unselectedColor = if(darkTheme) colorUnselectedDark else colorUnselectedLight
    val selectedColor = if(darkTheme) colorSelectedDark else colorSelectedLight
    val rippleColor = if (darkTheme) colorRippleDark else colorRippleLight
    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()

        repeat(items.size) { tabWidthStateList.add(0.dp) }

        tabWidthStateList
    }

    Column {
        TabRow(
            divider = { Lined() },
            indicator = { positions ->
                val current = positions[indexSelected]
                val tabWidth =  tabWidths[indexSelected]

                Indicatored(
                    currentTabPosition = current,
                    tabWidth = tabWidth,
                    color = selectedColor)
            },
            selectedTabIndex = indexSelected,
            contentColor = unselectedColor,
            backgroundColor = Color.Transparent) {

            items.forEachIndexed { index, item ->

                Tabed(
                    text = item,
                    selected = index == indexSelected,
                    unselectedContentColor = unselectedColor,
                    selectedContentColor = selectedColor,
                    rippleColor = rippleColor,
                    onClick = { onChanged(index) },
                    onTextLayout = { layoutResult ->
                        val width = with(density) { layoutResult.size.width.toDp() }

                        tabWidths[indexSelected] = width
                    }
                )
            }
        }
    }
}

@Composable
private fun Indicatored(currentTabPosition: TabPosition, tabWidth: Dp, color: Color){
    val shape = RoundedCornerShape(topStart = radiusIndicator, topEnd = radiusIndicator)
    val modifier = Modifier
        .customTabIndicatorOffset(currentTabPosition = currentTabPosition, tabWidth = tabWidth)
        .clip(shape)

    TabRowDefaults.Indicator(
        modifier = modifier,
        height = heightIndicator,
        color = color
    )
}

@Composable
private fun Tabed(text: String, selected: Boolean, unselectedContentColor: Color,
                  selectedContentColor: Color, rippleColor: Color, onClick: () -> Unit,
                  onTextLayout: (TextLayoutResult) -> Unit = {}){

    val contentColor = if(selected) selectedContentColor else unselectedContentColor

    Tab(
        selected = selected,
        selectedContentColor = rippleColor,
        unselectedContentColor = unselectedContentColor,
        onClick = onClick){

        SpacerTabed()

        Text(
            text = text,
            fontSize = sizeTextFont,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = contentColor,
            onTextLayout = onTextLayout
        )

        SpacerTabed()
    }
}

@Composable
private fun SpacerTabed(){
    val modifier = Modifier
        .height(marginIndicator)

    Spacer(modifier = modifier)
}

private fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val duration = durationIndicatorAnimation
    val easing = easingIndicatorAnimation
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = duration, easing = easing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = duration, easing = easing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

/*---------------------------------------------PREVIEW--------------------------------------------*/

private const val SAMPLE_TEXT = "Sample"
private val sampleItems = listOf(SAMPLE_TEXT, SAMPLE_TEXT, SAMPLE_TEXT)

@Preview
@Composable
private fun LightMode() = TabRowedPreview()

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkMode() = TabRowedPreview()

@Composable
private fun TabRowedPreview() = Previewed {
    TabRowed(items = sampleItems, indexSelected = 0, onChanged = {})
}
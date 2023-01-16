package com.baec23.composetemplate.ui.comp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.baec23.composetemplate.ui.screen.samplescreen.TAG
import com.baec23.composetemplate.util.WrappingList
import com.baec23.composetemplate.util.mutableWrappingListOf
import kotlinx.coroutines.delay
import kotlin.math.abs
import kotlin.math.round

@Composable
fun TimePicker() {
    val validHours = mutableWrappingListOf<Int>()
    val validMinutes = mutableWrappingListOf<Int>()
    for (i in 1..12) {
        validHours.add(i)
    }
    for (i in 0..59) {
        validMinutes.add(i)
    }
    val hoursList = validHours.toWrappingList()
    val minutesList = validMinutes.toWrappingList()

    Row(modifier = Modifier.fillMaxWidth()){
        TimePickerSpinner2(modifier = Modifier.width(100.dp), list = hoursList)
        TimePickerSpinner2(modifier = Modifier.width(100.dp), list = minutesList)
    }

}

@Composable
fun TimePickerSpinner2(
    modifier: Modifier = Modifier,
    list: WrappingList<Int>,
    textStyle: TextStyle = MaterialTheme.typography.displaySmall,
) {
    var scrollOffset by remember { mutableStateOf(0f) }
    val scrollableState = rememberScrollableState { delta ->
        scrollOffset += delta / 2
        delta
    }

    val itemHeight = (textStyle.lineHeight.value + textStyle.lineHeight.value / 2)
    val currMidPoint = scrollOffset / itemHeight
    val currItemIndex = round(currMidPoint).toInt() * -1
    val offsetFromMidPoint = abs(currItemIndex) - abs(currMidPoint)
    val mult = if (currMidPoint > 0) -1f else 1f
    val currItemOffset = offsetFromMidPoint * itemHeight * 2 * mult

    LaunchedEffect(scrollableState.isScrollInProgress) {
        if (!scrollableState.isScrollInProgress) {
            val target = currItemIndex * -1 * itemHeight
            var diff = abs(scrollOffset - target)
            while (diff >= 1f) {
                Log.d(TAG, "TimePickerSpinner2: scrollOffset : $scrollOffset | target : $target | diff : $diff")
                if (scrollOffset > target) {
                    scrollOffset -= diff / 5
                } else {
                    scrollOffset += diff / 5
                }
                diff = abs(scrollOffset - target)
                delay(10)
            }
            scrollOffset = target
        }
    }

    Column(
        modifier = modifier
            .withVerticalFade()
            .scrollable(
                orientation = Orientation.Vertical, state = scrollableState
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.graphicsLayer { translationY = currItemOffset },
            text = "${list[currItemIndex - 2]}",
            style = textStyle
        )
        Text(
            modifier = Modifier.graphicsLayer { translationY = currItemOffset },
            text = "${list[currItemIndex - 1]}",
            style = textStyle
        )
        Text(
            modifier = Modifier
                .scale(1.5f - 0.5f * (abs(currItemOffset) / itemHeight))
                .graphicsLayer { translationY = currItemOffset },
            text = "${list[currItemIndex]}",
            style = textStyle
        )
        Text(
            modifier = Modifier.graphicsLayer { translationY = currItemOffset },
            text = "${list[currItemIndex + 1]}",
            style = textStyle
        )
        Text(
            modifier = Modifier.graphicsLayer { translationY = currItemOffset },
            text = "${list[currItemIndex + 2]}",
            style = textStyle
        )
    }

}

@Composable
fun SpinnerItem(
    indexOffset: Int,
    scrollOffset: Float,
    text: String,
) {
    Text(
        modifier = Modifier.graphicsLayer { translationY = scrollOffset },
        text = text,
    )
}

internal fun Modifier.withVerticalFade(): Modifier {
    return this.then(graphicsLayer {
        alpha = 0.99f
    }.drawWithContent {
        val colors = listOf(
            Color.Transparent, Color.Black, Color.Transparent
        )
        drawContent()
        drawRect(
            size = Size(width = size.width, height = size.height), brush = Brush.verticalGradient(
                colors,
                endY = size.height,
            ), blendMode = BlendMode.DstIn
        )
    })
}

@Composable
fun TimePickerSpinner(
    modifier: Modifier = Modifier,
    list: WrappingList<Int>,
) {
    var currIndex = 0
    val baseStyle = MaterialTheme.typography.displaySmall
    val subStyle1 = baseStyle.copy(fontSize = baseStyle.fontSize * 1f)
    val subStyle2 = baseStyle.copy(fontSize = baseStyle.fontSize * 1f)
    val subStyle3 = baseStyle.copy(fontSize = baseStyle.fontSize * 1f)
    val lineHeight = baseStyle.lineHeight.value
    var offset by remember { mutableStateOf(0f) }
    var isScrolling by remember { mutableStateOf(false) }

    currIndex = round(offset / lineHeight).toInt()
    val transY = (currIndex * lineHeight - offset) * 3
    Log.d(TAG, "TimePickerSpinner: $transY")
    val scrollState = rememberScrollableState { delta ->
//                    Log.d(TAG, "TimePickerSpinner: $delta")
        offset -= delta / 2
        delta
    }

//    val displayOffset by animateFloatAsState(targetValue = if(isScrolling) offset else offset)

    Column(modifier = modifier
        .width(100.dp)
        .graphicsLayer {
            alpha = 0.99f
            translationY = transY
        }
        .drawWithContent {
            val colors = listOf(
                Color.Transparent, Color.Black, Color.Transparent
            )
            drawContent()
            drawRect(
                size = Size(width = size.width, height = size.height),
                brush = Brush.verticalGradient(
                    colors,
                    endY = size.height,
                ),
                blendMode = BlendMode.DstIn
            )
        }
        .scrollable(
            orientation = Orientation.Vertical, state = scrollState
        ), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            modifier = Modifier.scale(1f - (0.5f * (lineHeight * 3 / (offset / lineHeight - lineHeight * 3)))),
            text = "${list[currIndex - 3]}",
            style = subStyle3
        )
        Text(
            modifier = Modifier.scale(1f - (0.5f * (lineHeight * 3 / (offset / lineHeight - lineHeight * 3)))),
            text = "${list[currIndex - 2]}",
            style = subStyle2
        )
        Text(
            modifier = Modifier.scale(1f - (0.5f * (lineHeight * 3 / (offset / lineHeight - lineHeight * 3)))),
            text = "${list[currIndex - 1]}",
            style = subStyle1
        )
        Text(
            modifier = Modifier.scale(1f - (0.5f * (lineHeight * 3 / (offset / lineHeight - lineHeight * 3)))),
            text = "${list[currIndex]}",
            style = baseStyle
        )
        Text(
//            modifier = Modifier.graphicsLayer { translationY = offset % lineHeight },
            text = "${list[currIndex + 1]}", style = subStyle1
        )
        Text(
//            modifier = Modifier.graphicsLayer { translationY = offset % lineHeight },
            text = "${list[currIndex + 2]}", style = subStyle2
        )
        Text(
//            modifier = Modifier.graphicsLayer { translationY = offset % lineHeight },
            text = "${list[currIndex + 3]}", style = subStyle3
        )
    }
}

package com.baec23.composetemplate.ui.comp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.baec23.composetemplate.ui.screen.samplescreen.TAG
import com.baec23.composetemplate.util.WrappingList
import com.baec23.composetemplate.util.mutableWrappingListOf
import kotlinx.coroutines.delay
import java.lang.Float.min
import kotlin.math.abs
import kotlin.math.round

@Composable
fun TimePicker() {
    val validHours = mutableWrappingListOf<String>()
    val validMinutes = mutableWrappingListOf<String>()
    for (i in 1..12) {
        validHours.add("$i")
    }
    for (i in 0..59) {
        validMinutes.add("$i")
    }
    val hoursList = validHours.toWrappingList()
    val minutesList = validMinutes.toWrappingList()

//    Row(modifier = Modifier.fillMaxWidth()) {
//        TimePickerSpinner2(modifier = Modifier.width(100.dp), list = hoursList)
//        TimePickerSpinner2(modifier = Modifier.width(100.dp), list = minutesList)
//    }
    val itemHeightPx = with(LocalDensity.current) { 44.dp.toPx() }
    val totalHeight = itemHeightPx * validHours.size

    val viewPortOffset = 5 * itemHeightPx / 2


    var scrollOffset by remember { mutableStateOf(totalHeight + itemHeightPx / 2 - viewPortOffset) }
    val scrollableState = rememberScrollableState { delta ->
        scrollOffset = totalHeight + scrollOffset % totalHeight - delta / 2
        delta
    }
    var currItem by remember { mutableStateOf(hoursList[((scrollOffset % totalHeight) / totalHeight).toInt()]) }

    LaunchedEffect(scrollOffset) {
        currItem = hoursList[((scrollOffset + viewPortOffset) / itemHeightPx).toInt()]
    }


    Row {
        TimePickerSpinner4(
            modifier = Modifier.weight(1f),
            items = hoursList,
            onSelectedItemChanged = {}
        )
    }
}

@Composable
fun TimePickerSpinner4(
    modifier: Modifier = Modifier,
    items: List<String>,
    numRows: Int = 5,
    textStyle: TextStyle = MaterialTheme.typography.displaySmall,
//    scrollOffset: Float,
//    scrollableState: ScrollableState,
    initialItemIndex: Int = 0,
    onSelectedItemChanged: (Int) -> Unit,
) {
    val itemHeightDp = with(LocalDensity.current) { textStyle.lineHeight.toDp() }
    val itemHeightPx = with(LocalDensity.current) { textStyle.lineHeight.toPx() }
    val totalHeight = items.size * itemHeightPx
    val viewportHeight = numRows * itemHeightPx
    val columnScrollState = rememberScrollState()
//    var scrollOffset by remember { mutableStateOf(totalHeight + itemHeightPx/2 - viewportHeight/2) }
    var scrollOffset by remember { mutableStateOf(totalHeight) }
    var midPoint by remember { mutableStateOf (scrollOffset + viewportHeight/2) }
    var currItemIndex by remember { mutableStateOf(0) }

    LaunchedEffect(scrollOffset) {
        midPoint = scrollOffset + viewportHeight/2
        currItemIndex = (midPoint/itemHeightPx).toInt() % items.size
        columnScrollState.scrollTo(scrollOffset.toInt())
    }

    Box(
        modifier = modifier
            .height(itemHeightDp * numRows)
            .background(Color.DarkGray)
            .scrollable(
                state = rememberScrollableState { delta ->
                    scrollOffset = totalHeight + scrollOffset % totalHeight - delta / 2
                    delta
                },
                orientation = Orientation.Vertical
            )
    ) {
        Text(modifier = Modifier.align(Alignment.CenterStart), text = "currItemIndex = $currItemIndex | scrollOffset = $scrollOffset", color = Color.Red)

        Column(modifier = Modifier.verticalScroll(state = columnScrollState, enabled = false)) {
            repeat(3) {
                items.forEachIndexed { index, item ->

                    val idealPosition = (index * itemHeightPx) + itemHeightPx/2
                    if(index == currItemIndex){
                        Log.d(TAG, "TimePickerSpinner4: for index $index midPoint = $midPoint and idealPosition = $idealPosition")
                        val distanceFromMidPoint = abs(midPoint%totalHeight - idealPosition)
                        Log.d(TAG, "TimePickerSpinner4: distanceFromMidPoint for index $index is $distanceFromMidPoint")
                    }

                    TimePickerSpinnerItem(
                        modifier = Modifier.height(itemHeightDp),
                        text = item,
                        textStyle = textStyle,
                        fromCenterScale = 1f - (0.25f * abs(midPoint%totalHeight - idealPosition)/itemHeightPx)
                    )
                }
            }
        }
        Box(modifier = Modifier
            .align(Alignment.CenterStart)
            .height(4.dp)
            .width(25.dp)
            .background(Color.Blue))
    }
//
//
//    val scrollState = rememberScrollState()
//    LaunchedEffect(scrollOffset) {
//        scrollState.scrollTo(scrollOffset.toInt())
//    }
//
//    val itemHeightPx = with(LocalDensity.current) { itemHeight.toPx() }
//    val totalHeight = itemHeightPx * items.size
//
//    Box(
//        modifier = modifier
//            .height(itemHeight * 5)
//            .fillMaxWidth()
//            .background(Color.DarkGray)
//            .scrollable(state = scrollableState, orientation = Orientation.Vertical)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(state = scrollState, enabled = false)
//        ) {
//            repeat(3) {
//                items.forEach { item ->
//                    Text(
//                        modifier = Modifier.height(itemHeight),
//                        text = item,
//                        style = MaterialTheme.typography.displaySmall
//                    )
//                }
//            }
//        }
//    }
}

@Composable
fun TimePickerSpinnerItem(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle,
    fromCenterScale: Float,
) {
    Text(modifier = modifier.scale(abs(fromCenterScale)), text = text, style = textStyle)
}

@Composable
fun TimePickerSpinner3(
    modifier: Modifier = Modifier,
    items: WrappingList<String>,
    height: Dp,
    itemHeight: Dp,
    currScrollOffset: Float,
    scrollableState: ScrollableState,
    textStyle: TextStyle = MaterialTheme.typography.displaySmall,
) {
    val viewPortHeightPx = with(LocalDensity.current) { height.toPx() }
    val itemHeightPx = with(LocalDensity.current) { itemHeight.toPx() }
    val totalHeightPx = itemHeightPx * items.size
    val topBound = (currScrollOffset - (viewPortHeightPx / 2)) % totalHeightPx
    val bottomBound = (currScrollOffset + (viewPortHeightPx / 2)) % totalHeightPx
    val visibleItemIndexes = getIndexesWithinBounds(
        itemHeight = itemHeightPx,
        numItems = items.size,
        topBound = topBound,
        bottomBound = bottomBound
    )
    var currIndex = round(currScrollOffset / itemHeightPx).toInt()
    Log.d(TAG, "TimePickerSpinner3: $visibleItemIndexes")
    Log.d(TAG, "TimePickerSpinner3: topBound = $topBound | bottomBound = $bottomBound")

    Column(modifier = modifier.scrollable(state = scrollableState, Orientation.Vertical)) {
        visibleItemIndexes.forEach {
            Text(
                modifier = Modifier.scale(if (currIndex == it) 1.5f else 1f),
                text = items[it],
                style = textStyle
            )
        }
    }
}

private fun getIndexesWithinBounds(
    itemHeight: Float,
    numItems: Int,
    topBound: Float,
    bottomBound: Float
): List<Int> {
    val toReturn = mutableListOf<Int>()
    val totalHeight = numItems * itemHeight
    var mTopBound = topBound
    var mBottomBound = bottomBound
    while (mTopBound < 0) {
        mTopBound += totalHeight
    }
    while (mBottomBound < 0) {
        mBottomBound += totalHeight
    }

    if (mTopBound > mBottomBound) {
        for (i in 0 until numItems) {
            val localTop = i * itemHeight
            val localBottom = (i + 1) * itemHeight
            if (localTop in mTopBound..totalHeight || localBottom in mTopBound..totalHeight) {
                toReturn.add(i)
            }
        }
        for (i in 0 until numItems) {
            val localTop = i * itemHeight
            val localBottom = (i + 1) * itemHeight
            if (localTop in 0f..mBottomBound || localBottom in 0f..mBottomBound) {
                toReturn.add(i)
            }
        }
    } else {
        for (i in 0 until numItems) {
            val localTop = i * itemHeight
            val localBottom = (i + 1) * itemHeight
            if (localTop in mTopBound..mBottomBound || localBottom in mTopBound..mBottomBound) {
                toReturn.add(i)
            }
        }
    }
    return toReturn.toList()
}
//
//@Composable
//fun TimePickerSpinnerItem(
//    modifier: Modifier = Modifier,
//    text: String,
//    localOffset: Float,
//) {
//}

@Composable
fun TimePickerSpinnerLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measureables, constraints ->
            val placeables = measureables.map { measureable ->
                measureable.measure(constraints)
            }
            val numItems = placeables.size
            val midIndex = numItems / 2
            val myWidth = placeables.maxOf { it.width }
            val myHeight = placeables.sumOf { it.height }
            val midItemHeight = placeables[midIndex].height
            var yPlacementOffset = 0;
            layout(myWidth, myHeight) {
                placeables.forEachIndexed { index, placeable ->
                    val indexOffset = abs(index - midIndex)
                    val itemHeight = (midItemHeight * (1f - (0.2f * indexOffset))).toInt()
                    placeable.placeRelative(
                        x = 0,
                        y = yPlacementOffset
                    )
                    yPlacementOffset += itemHeight
                }
            }
        })
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
                Log.d(
                    TAG,
                    "TimePickerSpinner2: scrollOffset : $scrollOffset | target : $target | diff : $diff"
                )
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

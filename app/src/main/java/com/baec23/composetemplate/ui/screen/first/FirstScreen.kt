package com.baec23.composetemplate.ui.screen.first

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import coil.compose.SubcomposeAsyncImage
import com.baec23.ludwig.component.section.DisplaySection

const val firstScreenRoute = "first_screen_route"

fun NavGraphBuilder.firstScreen() {
    composable(route = firstScreenRoute) {
        FirstScreen()
    }
}

fun NavController.navigateToFirstScreen(navOptions: NavOptions? = null) {
    navigate(route = firstScreenRoute, navOptions = navOptions)
}

@Composable
fun FirstScreen(
    viewModel: FirstViewModel = hiltViewModel()
) {
    val currScreenWidthPx =
        with(LocalDensity.current) { Dp(LocalConfiguration.current.screenWidthDp.toFloat()).toPx() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
    ) {

    }
}

fun Modifier.fadeBottom(): Modifier {
    return this
        .graphicsLayer { alpha = 0.99f }
        .drawWithContent {
            val colors = listOf(
                Color.Transparent,
                Color.Black,
            )
            drawContent()
            val gradientHeight = size.height
            drawRect(
                size = Size(width = size.width, height = gradientHeight),
                brush = Brush.verticalGradient(
                    colors,
                    startY = gradientHeight,
                    endY = 0f
                ),
                blendMode = BlendMode.DstIn
            )
        }
}
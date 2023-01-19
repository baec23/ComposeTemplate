package com.baec23.composetemplate.ui.screen.third

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val thirdScreenRoute = "third_screen_route"

fun NavGraphBuilder.thirdScreen() {
    composable(route = thirdScreenRoute) {
        ThirdScreen()
    }
}

fun NavController.navigateToThirdScreen(navOptions: NavOptions? = null) {
    this.navigate(route = thirdScreenRoute, navOptions = navOptions)
}

@Composable
fun ThirdScreen(
    viewModel: ThirdViewModel = hiltViewModel()
) {
}
package com.baec23.composetemplate.ui.screen.second

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val secondScreenRoute = "second_screen_route"

fun NavGraphBuilder.secondScreen() {
    composable(route = secondScreenRoute) {
        SecondScreen()
    }
}

fun NavController.navigateToSecondScreen(navOptions: NavOptions? = null) {
    this.navigate(route = secondScreenRoute, navOptions = navOptions)
}

@Composable
fun SecondScreen(
    viewModel: SecondViewModel = hiltViewModel()
) {
}
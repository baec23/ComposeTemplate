package com.baec23.composetemplate.ui.screen.first

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
    ) {
        repeat(20){
            Text("Lalalalala", style= MaterialTheme.typography.displayMedium)
        }
    }
}
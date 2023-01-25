package com.baec23.composetemplate.ui.screen.main.second

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.baec23.ludwig.component.section.DisplaySection

const val secondScreenRoute = "second_screen_route"
fun NavGraphBuilder.secondScreen() {
    composable(route = secondScreenRoute) {
        SecondScreen()
    }
}

fun NavController.navigateToSecondScreen(navOptions: NavOptions? = null) {
    navigate(route = secondScreenRoute, navOptions = navOptions)
}

@Composable
fun SecondScreen(
    viewModel: SecondViewModel = hiltViewModel()
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        DisplaySection(headerText = "Second Screen") {
            Text("This is the second screen's content")
        }
    }
}
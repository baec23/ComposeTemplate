package com.baec23.composetemplate.ui.screen.main.third

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
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.ludwig.component.section.DisplaySection

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
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        DisplaySection(headerText = "Third Screen") {
            StatefulButton(text = "Logout") {
                viewModel.onEvent(ThirdUiEvent.LogoutPressed)
            }
        }
    }
}
package com.baec23.composetemplate.ui.screen.sample1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.ludwig.component.inputfield.InputField

fun NavGraphBuilder.sample1Screen(
) {
    composable(
        route = "sample1_route",
    ) {
        val viewModel: Sample1ViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()
        Sample1Screen(
            uiState = uiState,
            onNameChange = { viewModel.onEvent(Sample1UiEvent.OnNameChange(it)) },
            onNextScreenPress = { viewModel.onEvent(Sample1UiEvent.NextScreenPress) })
    }
}

@Composable
fun Sample1Screen(
    uiState: Sample1UiState,
    onNameChange: (String) -> Unit,
    onNextScreenPress: () -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        InputField(
            value = uiState.name,
            onValueChange = {
                onNameChange(it)
            },
            label = "Name"
        )
        StatefulButton(text = "Go to next screen") {
            onNextScreenPress()
        }
    }
}
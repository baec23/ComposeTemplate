package com.baec23.composetemplate.ui.screen.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.composetemplate.navigation.NavService
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.ludwig.component.inputfield.InputField
import com.baec23.ludwig.component.inputfield.PasswordInputField
import com.baec23.ludwig.component.section.DisplaySection

const val signupScreenRoute = "signup_screen_route"
fun NavGraphBuilder.signupScreen() {
    composable(route = signupScreenRoute) {
        SignUpScreen()
    }
}

fun NavService.navigateToSignUpScreen(navOptions: NavOptions? = null) {
    navController.navigate(route = signupScreenRoute, navOptions = navOptions)
}

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val username by viewModel.username.collectAsState()
    val password1 by viewModel.password1.collectAsState()
    val password2 by viewModel.password2.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        DisplaySection(headerText = "Sign Up", horizontalAlignment = Alignment.CenterHorizontally) {
            InputField(
                value = username,
                onValueChange = { viewModel.onEvent(SignUpUiEvent.UsernameChanged(it)) },
                label = "Username"
            )
            PasswordInputField(
                value = password1,
                onValueChange = { viewModel.onEvent(SignUpUiEvent.Password1Changed(it)) },
                label = "Password"
            )
            PasswordInputField(
                value = password2,
                onValueChange = { viewModel.onEvent(SignUpUiEvent.Password2Changed(it)) },
                label = "Password Again"
            )
            StatefulButton(text = "Sign Up") {
                viewModel.onEvent(SignUpUiEvent.SignUpButtonPressed)
            }
        }
    }

}
package com.baec23.composetemplate.ui.screen.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.baec23.ludwig.component.button.ButtonState
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.ludwig.component.inputfield.InputField
import com.baec23.ludwig.component.inputfield.PasswordInputField
import com.baec23.ludwig.component.section.DisplaySection

const val loginScreenRoute = "login_screen_route"
fun NavGraphBuilder.loginScreen() {
    composable(route = loginScreenRoute) {
        LoginScreen()
    }
}

fun NavService.navigateToLoginScreen(navOptions: NavOptions? = null) {
    navController.navigate(route = loginScreenRoute, navOptions = navOptions)
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val isFormValid by viewModel.isFormValid.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        DisplaySection(headerText = "Login") {
            InputField(
                value = username,
                onValueChange = { viewModel.onEvent(LoginUiEvent.UsernameChanged(it)) },
                label = "Username"
            )
            PasswordInputField(
                value = password,
                onValueChange = { viewModel.onEvent(LoginUiEvent.PasswordChanged(it)) },
                label = "Password",
                errorMessage = "Password must be at least 4 characters"
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatefulButton(text = "Login", state = if(isFormValid) ButtonState.Enabled else ButtonState.Disabled) {
                    viewModel.onEvent(LoginUiEvent.LoginButtonPressed)
                }
                StatefulButton(text = "Sign Up") {
                    viewModel.onEvent(LoginUiEvent.SignUpButtonPressed)
                }
            }
        }
    }
}
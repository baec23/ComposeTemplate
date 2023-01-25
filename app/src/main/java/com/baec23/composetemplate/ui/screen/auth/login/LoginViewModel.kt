package com.baec23.composetemplate.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.composetemplate.navigation.NavService
import com.baec23.composetemplate.navigation.navgraph.navigateToMainNavGraph
import com.baec23.composetemplate.ui.screen.auth.signup.navigateToSignUpScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navService: NavService,
) : ViewModel() {

    private val _username: MutableStateFlow<String> = MutableStateFlow("")
    val username = _username.asStateFlow()
    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    val password = _password.asStateFlow()

    val isFormValid = combine(_username, _password) { username, password ->
        username.isNotBlank() && password.length >= 4
    }.stateIn(scope = viewModelScope, started = SharingStarted.Lazily, initialValue = false)

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            LoginUiEvent.LoginButtonPressed -> navService.navigateToMainNavGraph()
            LoginUiEvent.SignUpButtonPressed -> navService.navigateToSignUpScreen()
            is LoginUiEvent.UsernameChanged -> _username.value = event.username
            is LoginUiEvent.PasswordChanged -> _password.value = event.password
        }
    }
}

sealed class LoginUiEvent {
    data class UsernameChanged(val username: String) : LoginUiEvent()
    data class PasswordChanged(val password: String) : LoginUiEvent()
    object LoginButtonPressed : LoginUiEvent()
    object SignUpButtonPressed : LoginUiEvent()
}
package com.baec23.composetemplate.ui.screen.auth.signup

import androidx.lifecycle.ViewModel
import com.baec23.composetemplate.navigation.NavService
import com.baec23.composetemplate.ui.screen.auth.login.navigateToLoginScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val navService: NavService
) : ViewModel() {

    private val _username: MutableStateFlow<String> = MutableStateFlow("")
    val username = _username.asStateFlow()
    private val _password1: MutableStateFlow<String> = MutableStateFlow("")
    val password1 = _password1.asStateFlow()
    private val _password2: MutableStateFlow<String> = MutableStateFlow("")
    val password2 = _password2.asStateFlow()
    fun onEvent(event: SignUpUiEvent) {
        when (event) {
            is SignUpUiEvent.UsernameChanged -> _username.value = event.username
            is SignUpUiEvent.Password1Changed -> _password1.value = event.password
            is SignUpUiEvent.Password2Changed -> _password2.value = event.password
            SignUpUiEvent.SignUpButtonPressed -> navService.navigateToLoginScreen()
        }
    }
}
sealed class SignUpUiEvent {
    data class UsernameChanged(val username: String): SignUpUiEvent()
    data class Password1Changed(val password: String): SignUpUiEvent()
    data class Password2Changed(val password: String): SignUpUiEvent()
    object SignUpButtonPressed: SignUpUiEvent()
}
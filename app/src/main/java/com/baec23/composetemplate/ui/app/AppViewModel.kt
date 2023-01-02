package com.baec23.composetemplate.ui.app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.baec23.composetemplate.service.NavService
import com.baec23.composetemplate.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    navService: NavService,
    val snackbarService: SnackbarService,
    val navController: NavHostController
) : ViewModel() {

    val currNavScreen = navService.currNavigationDestination
    private val _backdropShown: MutableState<Boolean> = mutableStateOf(false)
    val backdropShown: State<Boolean> = _backdropShown

    fun onEvent(event: AppUiEvent) {
        when (event) {
//            is AppUiEvent.NavbarButtonPressed -> navService.navigate(event.destinationScreen)
            AppUiEvent.ToggleBackdrop -> _backdropShown.value = !_backdropShown.value
            AppUiEvent.LogoutPressed -> snackbarService.showSnackbar("Logout Pressed!")
        }
    }
}

sealed class AppUiEvent {
//    data class NavbarButtonPressed(val destinationScreen: NavScreen) : AppUiEvent()
    object ToggleBackdrop : AppUiEvent()
    object LogoutPressed : AppUiEvent()
}

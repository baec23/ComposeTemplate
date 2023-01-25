package com.baec23.composetemplate.ui.screen.main.third

import androidx.lifecycle.ViewModel
import com.baec23.composetemplate.navigation.NavService
import com.baec23.composetemplate.navigation.navgraph.navigateToAuthNavGraph
import com.baec23.composetemplate.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThirdViewModel @Inject constructor(
    private val snackbarService: SnackbarService,
    private val navService: NavService
) : ViewModel() {
    fun onEvent(event: ThirdUiEvent) {
        when (event) {
            ThirdUiEvent.LogoutPressed -> {
                snackbarService.showSnackbar("Logged out!")
                navService.navigateToAuthNavGraph()
            }
        }
    }
}

sealed class ThirdUiEvent {
    object LogoutPressed : ThirdUiEvent()
}
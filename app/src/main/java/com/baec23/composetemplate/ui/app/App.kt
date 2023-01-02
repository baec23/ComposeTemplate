package com.baec23.composetemplate.ui.app

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.composetemplate.navigation.RootNavHost
import com.baec23.composetemplate.ui.app.comp.TopBar

const val TAG: String = "App"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    appViewModel: AppViewModel = hiltViewModel(),
) {
    val currScreen by appViewModel.currNavScreen
    BackHandler() {

    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = appViewModel.snackbarService.snackbarState) },
        topBar = {
            if (currScreen.shouldShowNavBars) {
                TopBar(
                    modifier = Modifier.height(60.dp),
                    screenName = currScreen.displayName,
                    onToggleBackdrop = { appViewModel.onEvent(AppUiEvent.ToggleBackdrop) },
                    onLogout = { appViewModel.onEvent(AppUiEvent.LogoutPressed) },
                )
            }
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            RootNavHost(navController = appViewModel.navController)
        }
    }
}
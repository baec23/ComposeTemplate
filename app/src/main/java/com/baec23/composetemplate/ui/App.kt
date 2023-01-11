package com.baec23.composetemplate.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.baec23.composetemplate.ui.screen.samplescreen.sampleScreen
import com.baec23.composetemplate.ui.screen.samplescreen.sampleScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    appViewModel: AppViewModel = hiltViewModel(),
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = appViewModel.snackbarHostState.value) },
        topBar = {
            //TODO: TopBar
        },
        bottomBar = {
            //TODO: BottomBar
        }) {
        Column(
            modifier = Modifier.padding(it).padding(16.dp)
        ) {
            NavHost(
                navController = appViewModel.navController, startDestination = sampleScreenRoute
            ) {
                sampleScreen()
            }
        }
    }
}
package com.baec23.composetemplate.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baec23.composetemplate.navigation.Navigation

@Composable
fun App(
    viewModel: AppViewModel = hiltViewModel(),
) {
    Navigation(navService = viewModel.navService, snackbarService = viewModel.snackbarService)
}
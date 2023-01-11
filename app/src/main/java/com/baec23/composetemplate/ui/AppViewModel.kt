package com.baec23.composetemplate.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.baec23.composetemplate.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    val navController: NavHostController,
    snackbarService: SnackbarService,
) : ViewModel() {
    val snackbarHostState = mutableStateOf(snackbarService.snackbarState)

    fun onEvent(event: AppUiEvent) {

    }
}

sealed class AppUiEvent {}
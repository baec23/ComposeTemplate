package com.baec23.composetemplate.ui.screen.samplescreen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.baec23.composetemplate.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val navController: NavHostController,
    private val snackbarService: SnackbarService,
) : ViewModel() {
    fun onEvent(event: SampleUiEvent) {
        when (event) {
            SampleUiEvent.SampleButtonPressed -> snackbarService.showSnackbar("Button pressed!")
        }
    }
}

sealed class SampleUiEvent {
    object SampleButtonPressed : SampleUiEvent()
}
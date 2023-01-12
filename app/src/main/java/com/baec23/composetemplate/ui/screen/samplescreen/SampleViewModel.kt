package com.baec23.composetemplate.ui.screen.samplescreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    private val _showDatePicker: MutableState<Boolean> = mutableStateOf(false)
    val showDatePicker: State<Boolean> = _showDatePicker
    fun onEvent(event: SampleUiEvent) {
        when (event) {
            SampleUiEvent.SampleButtonPressed -> _showDatePicker.value = true
            SampleUiEvent.DatePickerDismissed -> _showDatePicker.value = false
        }
    }
}

sealed class SampleUiEvent {
    object SampleButtonPressed : SampleUiEvent()
    object DatePickerDismissed : SampleUiEvent()
}
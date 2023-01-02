package com.baec23.composetemplate.ui.screen.sample1

import androidx.lifecycle.ViewModel
import com.baec23.composetemplate.navigation.NavigationDestination
import com.baec23.composetemplate.service.NavService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class Sample1ViewModel @Inject constructor(
    private val navService: NavService
) : ViewModel() {
    private val _uiState = MutableStateFlow(Sample1UiState(name = ""))
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: Sample1UiEvent) {
        when (event) {
            is Sample1UiEvent.OnNameChange -> _uiState.value =
                _uiState.value.copy(name = event.name)

            Sample1UiEvent.NextScreenPress -> {
                navService.navigate(NavigationDestination.Sample2(sampleArg = uiState.value.name))
            }
        }
    }
}

data class Sample1UiState(
    val name: String
)

sealed class Sample1UiEvent {
    data class OnNameChange(val name: String) : Sample1UiEvent()
    object NextScreenPress : Sample1UiEvent()
}
package com.baec23.composetemplate.ui.screen.third

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThirdViewModel @Inject constructor() : ViewModel() {
//    fun onEvent(event: ThirdUiEvent) {
//        when (event) {
//        }
//    }
}

sealed class ThirdUiEvent {
}
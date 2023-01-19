package com.baec23.composetemplate.ui.screen.first

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor() : ViewModel() {
//    fun onEvent(event: FirstUiEvent) {
//        when (event) {
//        }
//    }
}

sealed class FirstUiEvent {
}
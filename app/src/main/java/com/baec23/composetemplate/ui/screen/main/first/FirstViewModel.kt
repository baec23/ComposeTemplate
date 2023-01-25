package com.baec23.composetemplate.ui.screen.main.first

import androidx.lifecycle.ViewModel
import com.baec23.composetemplate.model.SampleItem
import com.baec23.composetemplate.navigation.NavService
import com.baec23.composetemplate.repository.SampleItemRepository
import com.baec23.composetemplate.ui.screen.main.firstdetails.navigateToFirstDetailsScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val navService: NavService,
    sampleItemRepository: SampleItemRepository
) : ViewModel() {

    val allSampleItems = sampleItemRepository.allSampleItems
    fun onEvent(event: FirstUiEvent) {
        when (event) {
            is FirstUiEvent.OnSampleItemSelected -> {navService.navigateToFirstDetailsScreen(event.item.id)}
        }
    }
}

sealed class FirstUiEvent {
    data class OnSampleItemSelected(val item: SampleItem) : FirstUiEvent()
}
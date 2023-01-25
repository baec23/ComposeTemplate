package com.baec23.composetemplate.ui.screen.main.firstdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.composetemplate.model.SampleItem
import com.baec23.composetemplate.repository.SampleItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstDetailsViewModel @Inject constructor(
    private val sampleItemRepository: SampleItemRepository
) : ViewModel() {

    private val _currItem = MutableStateFlow<SampleItem?>(null)
    val currItem = _currItem.asStateFlow()
    fun setItemId(itemId: String) {
        viewModelScope.launch {
            _currItem.emit(sampleItemRepository.getSampleItemById(itemId))
        }
    }
}
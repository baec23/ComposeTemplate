package com.baec23.composetemplate.ui.screen.samplescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.composetemplate.ui.comp.TimePicker
import com.baec23.composetemplate.util.WrappingList
import com.baec23.composetemplate.util.mutableWrappingListOf
import com.baec23.ludwig.component.datepicker.DatePicker
import com.baec23.ludwig.component.section.DisplaySection
import java.time.LocalDate

const val sampleScreenRoute = "sample_screen_route"
const val TAG = "SampleScreen"

fun NavGraphBuilder.sampleScreen() {
    composable(route = sampleScreenRoute) {
        SampleScreen()
    }
}

fun NavController.navigateToSampleScreen(navOptions: NavOptions? = null) {
    this.navigate(route = sampleScreenRoute, navOptions = navOptions)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleScreen(
    viewModel: SampleViewModel = hiltViewModel()
) {
    var selectedDate by rememberSaveable { mutableStateOf(LocalDate.now()) }
    var pickerDialogShown by rememberSaveable { mutableStateOf(false) }
    var datePickerState = rememberDatePickerState()

    if (pickerDialogShown) {
        Dialog(onDismissRequest = { pickerDialogShown = false }) {

            androidx.compose.material3.DatePicker(datePickerState = datePickerState)

            DatePicker(
                initialDate = selectedDate,
                onDateSelectionFinalized = {
                    selectedDate = it
                    pickerDialogShown = false
                },
                onCancelled = { pickerDialogShown = false },
                shouldFinalizeOnSelect = false,
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DisplaySection(headerText = "Time Picker Test") {
            TimePicker()
        }
    }
}

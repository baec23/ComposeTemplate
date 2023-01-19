package com.baec23.composetemplate.ui.screen.samplescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.datepicker.DatePicker
import com.baec23.ludwig.component.section.DisplaySection
import com.baec23.ludwig.component.timepicker.TimePicker
import com.baec23.ludwig.component.timepicker.TimePickerType
import java.time.LocalDate
import java.time.LocalTime

const val TAG = "SampleScreen"
const val sampleScreenRoute = "sample_screen_route"

fun NavGraphBuilder.sampleScreen() {
    composable(route = sampleScreenRoute) {
        SampleScreen()
    }
}

fun NavController.navigateToSampleScreen(navOptions: NavOptions? = null) {
    this.navigate(route = sampleScreenRoute, navOptions = navOptions)
}

@Composable
fun SampleScreen(
    viewModel: SampleViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
    ) {
        repeat(20){
            Text("Lalalalala", style=MaterialTheme.typography.displayMedium)
        }
    }
}

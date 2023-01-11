package com.baec23.composetemplate.ui.screen.samplescreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.ludwig.component.section.DisplaySection
import com.baec23.ludwig.core.autoResizingText.AutoResizingText
import java.time.LocalDateTime
import java.time.format.TextStyle

const val sampleScreenRoute = "sample_screen_route"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.sampleScreen() {
    composable(route = sampleScreenRoute) {
        SampleScreen()
    }
}

fun NavController.navigateToSampleScreen(navOptions: NavOptions? = null) {
    this.navigate(route = sampleScreenRoute, navOptions = navOptions)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SampleScreen(
    viewModel: SampleViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DisplaySection(headerText = "Sample Screen Display Section") {
            StatefulButton(onClick = { viewModel.onEvent(SampleUiEvent.SampleButtonPressed) }) {
                AutoResizingText(
                    text = "Hello there this is auto resizing text that is very very long",
                    maxLines = 1
                )
            }
            DatePicker()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier, shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DatePickerHeader(selectedDate = LocalDateTime.now())
            DatePickerCalendarControls(selectedDate = LocalDateTime.now())
            DatePickerCalendar()
            DatePickerControls()
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerHeader(
    selectedDate: LocalDateTime,
) {
    val locale = LocalContext.current.resources.configuration.locales[0]

    val dayOfWeek = selectedDate.dayOfWeek.getDisplayName(TextStyle.SHORT, locale)
    val month = selectedDate.month.getDisplayName(TextStyle.SHORT, locale)
    val dayOfMonth = selectedDate.dayOfMonth

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Select date", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "$dayOfWeek, $month $dayOfMonth",
                style = MaterialTheme.typography.displaySmall
            )
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Date")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerCalendarControls(
    selectedDate: LocalDateTime
) {
    val locale = LocalContext.current.resources.configuration.locales[0]
    val month = selectedDate.month.getDisplayName(TextStyle.FULL, locale)
    val year = selectedDate.year

    var isDropdownExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.wrapContentSize(Alignment.TopStart)
        ) {
            Row(
                modifier = Modifier
                    .clickable { isDropdownExpanded = true }
            ) {
                Text(text = "$month $year", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.width(5.dp))
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Select Month")
            }
            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }) {
                DropdownMenuItem(text = {
                    Text(
                        text = "$month $year", style = MaterialTheme.typography.bodyLarge
                    )
                }, onClick = { /*TODO*/ })
            }
        }

        Row {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous Month"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next Month"
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerCalendar() {

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerCalendarHeader() {

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerCalendarButton(
    day: Int,
    isSelected: Boolean,
    isToday: Boolean,
) {
    Box(modifier = Modifier
        .clip(CircleShape)
        .background(MaterialTheme.colorScheme.primary)){
        Text(text = day.toString())
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerControls() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Row {
            TextButton(onClick = { /*TODO*/ }) {
                Text("Cancel")
            }
            Spacer(modifier = Modifier.width(5.dp))
            TextButton(onClick = { /*TODO*/ }) {
                Text("OK")
            }
        }
    }
}
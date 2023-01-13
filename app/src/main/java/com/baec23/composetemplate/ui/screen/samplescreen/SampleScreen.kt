package com.baec23.composetemplate.ui.screen.samplescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
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
import com.baec23.composetemplate.ui.DatePicker
import com.baec23.ludwig.component.section.DisplaySection
import java.time.LocalDate

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
    var selectedDate by rememberSaveable { mutableStateOf(LocalDate.now()) }
    var pickerDialogShown by rememberSaveable { mutableStateOf(false) }

    if (pickerDialogShown) {
        Dialog(onDismissRequest = { pickerDialogShown = false }) {
            DatePicker(
                initialDate = selectedDate,
                onDateSelectionFinalized = {
                    selectedDate = it
                    pickerDialogShown = false
                },
                onCancelled = { pickerDialogShown = false },
                shouldFinalizeOnSelect = true
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DisplaySection(headerText = "Sample Screen Display Section") {
            Text(modifier = Modifier.clickable {
                pickerDialogShown = true
            }, text = selectedDate.toString())
        }
    }

}
//
//@Composable
//fun MyHeader(selectedDate: LocalDate) {
//    DisplaySection(headerText = "This is my header") {
//        Text(text = selectedDate.toString())
//    }
//}
//
//@Composable
//fun MyFooter(
//    onIncrement: () -> Unit,
//    onDecrement: () -> Unit,
//) {
//    DisplaySection(headerText = "This is my footer") {
//        Row {
//            Button(onClick = onDecrement) {
//                Text("Decrement")
//            }
//            Button(onClick = onIncrement) {
//                Text("Increment")
//            }
//        }
//    }
//}
//
//@Composable
//fun MyContent() {
//    DisplaySection(headerText = "This is my content") {
//        Box(modifier = Modifier
//            .size(200.dp)
//            .background(Color.Red))
//    }
//}
//
//data class DatePickerState(
//    val todayDate: LocalDate,
//    val selectedDate: LocalDate,
//    val viewingDate: LocalDate,
//)
//
//
//@Composable
//fun DatePicker(
//    modifier: Modifier = Modifier,
//) {
//    var isYearSelectionActive by remember { mutableStateOf(false) }
//
//    val todayDate = LocalDate.now()
//    var state by remember {
//        mutableStateOf(
//            DatePickerState(
//                todayDate = todayDate,
//                selectedDate = todayDate,
//                viewingDate = todayDate
//            )
//        )
//    }
//
//    var selectedDate by remember { mutableStateOf(todayDate) }
//    var viewingDate by remember { mutableStateOf(todayDate) }
//
//    val selectableYears = mutableListOf(1900)
//    for (i in 1901..2100) {
//        selectableYears.add(i)
//    }
//    val selectedYearIndex = selectableYears.indexOf(selectedDate.year)
//    val gridState = rememberLazyGridState()
//
//    LaunchedEffect(true) {
//        gridState.scrollToItem(selectedYearIndex, scrollOffset = -148)
//    }
//
//    Card(
//        modifier = modifier,
//        shape = RoundedCornerShape(
//            topStart = 16.dp,
//            topEnd = 16.dp,
//            bottomStart = 12.dp,
//            bottomEnd = 12.dp
//        )
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
////            DatePickerHeader(selectedDate = selectedDate)
//            Divider()
//            DatePickerCalendarControls(viewingDate = viewingDate,
//                isYearSelectionActive = isYearSelectionActive,
//                onYearSelectionClick = {
//                    isYearSelectionActive = !isYearSelectionActive
//                },
//                onPrevMonthClick = {
//                    viewingDate = viewingDate.minusMonths(1)
//                },
//                onNextMonthClick = {
//                    viewingDate = viewingDate.plusMonths(1)
//                })
//            Box(
//                modifier = Modifier
//                    .padding(horizontal = 12.dp)
//                    .weight(1f, fill = false)
//            ) {
//                if (isYearSelectionActive) {
//                    DatePickerYearSelector(
//                        gridState = gridState,
//                        selectableYears = selectableYears,
//                        selectedYear = viewingDate.year,
//                        onYearSelect = {
//                            viewingDate = viewingDate.withYear(it)
////                            isYearSelectionActive = !isYearSelectionActive
//                        })
//                } else {
//                    DatePickerCalendar(selectedDate = selectedDate, viewingDate = viewingDate) {
//                        selectedDate = it
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//            DatePickerControls()
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//    }
//}
//
//@Composable
//fun DatePickerHeader(
//    datePickerState: DatePickerState,
//) {
//    val selectedDate = datePickerState.selectedDate
//    val locale = LocalContext.current.resources.configuration.locales[0]
//
//    val dayOfWeek = selectedDate.dayOfWeek.getDisplayName(TextStyle.SHORT, locale)
//    val month = selectedDate.month.getDisplayName(TextStyle.SHORT, locale)
//    val dayOfMonth = selectedDate.dayOfMonth
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 24.dp)
//    ) {
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(text = "Select date", style = MaterialTheme.typography.labelMedium)
//        Spacer(modifier = Modifier.height(36.dp))
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            Text(
//                text = "$dayOfWeek, $month $dayOfMonth",
//                style = MaterialTheme.typography.headlineLarge
//            )
//            IconButton(onClick = {}) {
//                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Date")
//            }
//        }
//        Spacer(modifier = Modifier.height(12.dp))
//    }
//}
//
//@Composable
//fun DatePickerContent() {
////    DatePickerCalendarControls(viewingDate = viewingDate,
////        isYearSelectionActive = isYearSelectionActive,
////        onYearSelectionClick = {
////            isYearSelectionActive = !isYearSelectionActive
////        },
////        onPrevMonthClick = {
////            viewingDate = viewingDate.minusMonths(1)
////        },
////        onNextMonthClick = {
////            viewingDate = viewingDate.plusMonths(1)
////        })
//}
//
//@Composable
//fun DatePickerControls() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(36.dp),
//        horizontalArrangement = Arrangement.End
//    ) {
//        Row {
//            TextButton(onClick = { /*TODO*/ }) {
//                Text("Cancel")
//            }
//            Spacer(modifier = Modifier.width(16.dp))
//            TextButton(onClick = { /*TODO*/ }) {
//                Text("OK")
//            }
//        }
//    }
//}
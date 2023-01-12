package com.baec23.composetemplate.ui.screen.samplescreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
            if(viewModel.showDatePicker.value){
                Dialog(onDismissRequest = { viewModel.onEvent(SampleUiEvent.DatePickerDismissed)}) {
                    DatePicker(
                        modifier = Modifier
                    )
                }
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
) {
    var isYearSelectionActive by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(LocalDateTime.now()) }
    var viewingDate by remember { mutableStateOf(LocalDateTime.now()) }

    val selectableYears = mutableListOf(1900)
    for (i in 1901..2100) {
        selectableYears.add(i)
    }
    val selectedYearIndex = selectableYears.indexOf(selectedDate.year)
    val gridState = rememberLazyGridState()

    LaunchedEffect(true){
        gridState.scrollToItem(selectedYearIndex, scrollOffset = -148)
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 12.dp,
            bottomEnd = 12.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            DatePickerHeader(selectedDate = selectedDate)
            Divider()
            DatePickerCalendarControls(viewingDate = viewingDate,
                isYearSelectionActive = isYearSelectionActive,
                onYearSelectionClick = {
                    isYearSelectionActive = !isYearSelectionActive
                },
                onPrevMonthClick = {
                    viewingDate = viewingDate.minusMonths(1)
                },
                onNextMonthClick = {
                    viewingDate = viewingDate.plusMonths(1)
                })
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1f, fill = false)
            ) {
                if (isYearSelectionActive) {
                    DatePickerYearSelector(
                        gridState = gridState,
                        selectableYears = selectableYears,
                        selectedYear = viewingDate.year,
                        onYearSelect = {
                            viewingDate = viewingDate.withYear(it)
//                            isYearSelectionActive = !isYearSelectionActive
                        })
                } else {
                    DatePickerCalendar(selectedDate = selectedDate, viewingDate = viewingDate) {
                        selectedDate = it
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            DatePickerControls()
            Spacer(modifier = Modifier.height(12.dp))
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Select date", style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(36.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "$dayOfWeek, $month $dayOfMonth",
                style = MaterialTheme.typography.headlineLarge
            )
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Date")
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerCalendarControls(
    viewingDate: LocalDateTime,
    isYearSelectionActive: Boolean,
    onYearSelectionClick: () -> Unit,
    onPrevMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit,
) {
    val locale = LocalContext.current.resources.configuration.locales[0]
    val month = viewingDate.month.getDisplayName(TextStyle.FULL, locale)
    val year = viewingDate.year

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.wrapContentSize(Alignment.CenterStart)
        ) {
            Row(
                modifier = Modifier.clickable { onYearSelectionClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(12.dp))
                val iconRotation = if (isYearSelectionActive) 180f else 0f
                Text(text = "$month $year", style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    modifier = Modifier
                        .rotate(iconRotation)
                        .size(24.dp),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Select Month"
                )
            }
        }
        if (!isYearSelectionActive) {
            Row {
                IconButton(
                    modifier = Modifier
                        .height(24.dp)
                        .width(40.dp),
                    onClick = { onPrevMonthClick() }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Previous Month"
                    )
                }
                IconButton(
                    modifier = Modifier
                        .height(24.dp)
                        .width(40.dp),
                    onClick = { onNextMonthClick() }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Next Month"
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerCalendar(
    selectedDate: LocalDateTime,
    viewingDate: LocalDateTime,
    onDateSelected: (LocalDateTime) -> Unit,
) {

    val currentMonth = viewingDate.month
    val firstDayOfMonth = viewingDate.withDayOfMonth(1).dayOfWeek
    val numDaysInMonth = currentMonth.length(isLeapYear(viewingDate))
    val displayOffset = if (firstDayOfMonth.value == 7) 0 else firstDayOfMonth.value

    val headerLabels = listOf("S", "M", "T", "W", "T", "F", "S")

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth(),
        columns = GridCells.Fixed(7),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        userScrollEnabled = false
    ) {
        items(7 + displayOffset + numDaysInMonth) {
            Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.Center) {
                when {
                    //Header Labels
                    it < headerLabels.size -> {
                        Text(
                            text = headerLabels[it],
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    //Prev Month Days
                    it < headerLabels.size + displayOffset -> {
                        Box(modifier = Modifier.size(1.dp)) {}
                    }

                    //Curr Month Days
                    else -> {
                        val day = it - headerLabels.size - displayOffset + 1
                        DatePickerCalendarButton(
                            day = day,
                            isSelected = day == selectedDate.dayOfMonth && viewingDate.month == selectedDate.month,
                            isToday = day == LocalDateTime.now().dayOfMonth && viewingDate.month == LocalDateTime.now().month
                        ) {
                            val selectedDate = viewingDate.withDayOfMonth(day)
                            onDateSelected(selectedDate)
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerYearSelector(
    gridState: LazyGridState = rememberLazyGridState(),
    selectableYears: List<Int>,
    selectedYear: Int,
    onYearSelect: (Int) -> Unit,
) {
    val selectedYearIndex = selectableYears.indexOf(selectedYear)
    val presentYear = LocalDateTime.now().year

    LazyVerticalGrid(
        modifier = Modifier.height(274.dp),
        columns = GridCells.Fixed(3),
        state = gridState,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(selectableYears.size) {
            val currItemYear = selectableYears[it]
            DatePickerSelectableYear(
                year = currItemYear,
                isSelected = currItemYear == selectedYear,
                isPresentYear = presentYear == currItemYear
            ) {
                onYearSelect(currItemYear)
            }
        }
    }
}

@Composable
fun DatePickerSelectableYear(
    year: Int,
    isSelected: Boolean,
    isPresentYear: Boolean,
    onYearSelect: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier
            .width(72.dp)
            .height(36.dp)
            .clip(CircleShape)
            .run {
                when {
                    isSelected -> this.background(MaterialTheme.colorScheme.primary)
                    isPresentYear -> this.border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )

                    else -> this
                }
            }
            .clickable { onYearSelect() }) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = year.toString(),
                textAlign = TextAlign.Center,
                color = when {
                    isSelected -> Color.White
                    isPresentYear -> MaterialTheme.colorScheme.primary
                    else -> Color.Unspecified
                },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun isLeapYear(date: LocalDateTime): Boolean {
    val year = date.year
    return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerCalendarButton(
    day: Int,
    isSelected: Boolean,
    isToday: Boolean,
    onClick: () -> Unit,
) {
    Box(modifier = Modifier
        .aspectRatio(1f)
        .clip(CircleShape)
        .run {
            if (isSelected) {
                this.background(MaterialTheme.colorScheme.primary)
            } else if (isToday) {
                this.border(
                    width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape
                )
            } else {
                this
            }
        }
        .clickable {
            onClick()
        }) {
        Text(
            modifier = Modifier.align(Alignment.Center), text = day.toString(), color = when {
                isSelected -> Color.White
                isToday -> MaterialTheme.colorScheme.primary
                else -> Color.Unspecified
            }, style = MaterialTheme.typography.bodySmall
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerControls() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Row {
            TextButton(onClick = { /*TODO*/ }) {
                Text("Cancel")
            }
            Spacer(modifier = Modifier.width(16.dp))
            TextButton(onClick = { /*TODO*/ }) {
                Text("OK")
            }
        }
    }
}
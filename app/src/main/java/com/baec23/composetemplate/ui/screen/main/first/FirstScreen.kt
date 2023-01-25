package com.baec23.composetemplate.ui.screen.main.first

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.composetemplate.model.SampleItem
import com.baec23.ludwig.component.section.DisplaySection

const val firstScreenRoute = "first_screen_route"

fun NavGraphBuilder.firstScreen() {
    composable(route = firstScreenRoute) {
        FirstScreen()
    }
}

fun NavController.navigateToFirstScreen(navOptions: NavOptions? = null) {
    navigate(route = firstScreenRoute, navOptions = navOptions)
}

@Composable
fun FirstScreen(
    viewModel: FirstViewModel = hiltViewModel()
) {
    val allSampleItems by viewModel.allSampleItems.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DisplaySection(headerText = "First Screen") {
            allSampleItems.forEach { item ->
                SampleItemCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.onEvent(FirstUiEvent.OnSampleItemSelected(item)) },
                    sampleItem = item
                )
            }
        }
    }
}

@Composable
fun SampleItemCard(
    modifier: Modifier = Modifier,
    sampleItem: SampleItem
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = sampleItem.text, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = sampleItem.subtext, style = MaterialTheme.typography.titleSmall)
        }
    }
}
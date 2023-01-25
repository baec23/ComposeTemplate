@file:OptIn(ExperimentalMaterial3Api::class)

package com.baec23.composetemplate.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.baec23.composetemplate.navigation.NavService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun DetailsScreen(
    content: @Composable () -> Unit,
) {
    val viewModel: DetailsScreenViewModel = hiltViewModel()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { DetailsTopBar { viewModel.navService.navigateUp() } }
    ) {
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }
}

@Composable
fun DetailsTopBar(
    onBackPress: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(onClick = onBackPress) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }
    }
}

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    val navService: NavService
) : ViewModel()
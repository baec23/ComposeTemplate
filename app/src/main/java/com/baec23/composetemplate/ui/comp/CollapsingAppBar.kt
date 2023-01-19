package com.baec23.composetemplate.ui.comp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
) {

    LaunchedEffect(scrollBehavior.state.collapsedFraction){
        Log.d("test", "CollapsingAppBar: ${scrollBehavior.state.collapsedFraction}")
    }

    TopAppBar(
        modifier = modifier,
        title = { AppBarTitle(modifier = Modifier.background(Color.Red)) },
        navigationIcon = { NavIcon(modifier = Modifier.background(Color.Green)) },
        actions = { Actions(modifier = Modifier.background(Color.Blue)) },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.DarkGray)
    )
}

@Composable
fun AppBarTitle(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text("This is title")
    }
}

@Composable
fun NavIcon(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text("This is NavIcon")
    }
}

@Composable
fun Actions(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text("This is Actions")
    }
}
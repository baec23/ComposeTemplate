package com.baec23.composetemplate.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.baec23.composetemplate.ui.comp.BottomNavigationBar
import com.baec23.composetemplate.ui.comp.BottomNavigationItem
import com.baec23.composetemplate.ui.screen.first.firstScreen
import com.baec23.composetemplate.ui.screen.first.firstScreenRoute
import com.baec23.composetemplate.ui.screen.second.secondScreen
import com.baec23.composetemplate.ui.screen.second.secondScreenRoute
import com.baec23.composetemplate.ui.screen.third.thirdScreen
import com.baec23.composetemplate.ui.screen.third.thirdScreenRoute

private val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = firstScreenRoute,
        iconImageVector = Icons.Default.Home,
        label = "First",
    ),
    BottomNavigationItem(
        route = secondScreenRoute,
        iconImageVector = Icons.Default.Info,
        label = "Second"
    ),
    BottomNavigationItem(
        route = thirdScreenRoute,
        iconImageVector = Icons.Default.Person,
        label = "Third"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    viewModel: AppViewModel = hiltViewModel(),
) {
    val appBarScrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(appBarScrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(hostState = viewModel.snackbarHostState.value) },
        topBar = {
        },
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier.height(50.dp),
                items = bottomNavigationItems,
                currNavScreenRoute = viewModel.navigationService.currNavScreenRoute.value,
                backgroundColor = Color.Unspecified,
                onBottomNavigationItemPressed = {
                    viewModel.onEvent(AppUiEvent.BottomNavBarButtonPressed(it))
                }
            )
        }) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            NavHost(navController = viewModel.navigationService.navController, graph = viewModel.navigationService.rootNavGraph)
        }
    }
}
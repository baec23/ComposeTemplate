package com.baec23.composetemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.baec23.composetemplate.ui.screen.sample1.sample1Screen
import com.baec23.composetemplate.ui.screen.sample2.sample2Screen

@Composable
fun RootNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Sample1.route,
    ) {
        sample1Screen()
        sample2Screen()
    }
}
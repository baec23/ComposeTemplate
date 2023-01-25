package com.baec23.composetemplate.navigation.navgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import com.baec23.composetemplate.navigation.NavService
import com.baec23.composetemplate.ui.screen.main.first.firstScreen
import com.baec23.composetemplate.ui.screen.main.first.firstScreenRoute
import com.baec23.composetemplate.ui.screen.main.firstdetails.firstDetailsScreen
import com.baec23.composetemplate.ui.screen.main.second.secondScreen
import com.baec23.composetemplate.ui.screen.main.second.secondScreenRoute
import com.baec23.composetemplate.ui.screen.main.third.thirdScreen
import com.baec23.composetemplate.ui.screen.main.third.thirdScreenRoute
import com.baec23.ludwig.component.navbar.BottomNavigationItem

const val mainNavGraphRoute = "main_nav_graph_route"
fun NavGraphBuilder.mainNavGraph() {
    navigation(startDestination = firstScreenRoute, route = mainNavGraphRoute) {
        firstScreen()
        secondScreen()
        thirdScreen()
        firstDetailsScreen()
    }
}

fun NavService.navigateToMainNavGraph() {
    navController.navigate(
        mainNavGraphRoute,
        navOptions = navOptions {
            popUpTo(navController.graph.id){
                inclusive = true
            }
        })
}

val bottomNavigationItems = listOf(
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
package com.baec23.composetemplate.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import com.baec23.composetemplate.navigation.NavService
import com.baec23.composetemplate.ui.screen.auth.login.loginScreen
import com.baec23.composetemplate.ui.screen.auth.login.loginScreenRoute
import com.baec23.composetemplate.ui.screen.auth.signup.signupScreen

const val authNavGraphRoute = "auth_nav_graph_route"
fun NavGraphBuilder.authNavGraph() {
    navigation(startDestination = loginScreenRoute, route = authNavGraphRoute) {
        loginScreen()
        signupScreen()
    }
}

fun NavService.navigateToAuthNavGraph() {
    navController.navigate(
        authNavGraphRoute,
        navOptions = navOptions {
            popUpTo(navController.graph.id){
                inclusive = true
            }
        })
}
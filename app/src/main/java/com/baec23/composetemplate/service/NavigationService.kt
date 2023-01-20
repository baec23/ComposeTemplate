package com.baec23.composetemplate.service

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import androidx.navigation.createGraph
import com.baec23.composetemplate.ui.screen.first.firstScreen
import com.baec23.composetemplate.ui.screen.first.firstScreenRoute
import com.baec23.composetemplate.ui.screen.second.secondScreen
import com.baec23.composetemplate.ui.screen.third.thirdScreen
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@ActivityScoped
class NavigationService(val navController: NavHostController) {

    private val _currNavScreenRoute: MutableState<String?> = mutableStateOf(null)
    val currNavScreenRoute: State<String?> = _currNavScreenRoute

    val rootNavGraph =
        navController.createGraph(startDestination = firstScreenRoute, route = null) {
            firstScreen()
            secondScreen()
            thirdScreen()
        }
    init {
        MainScope().launch {
            navController.currentBackStackEntryFlow.collect{
                _currNavScreenRoute.value = it.destination.route
            }
        }
    }
}
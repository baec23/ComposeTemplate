package com.baec23.composetemplate.service

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.baec23.composetemplate.navigation.NavigationDestination
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Singleton
import kotlin.reflect.full.createInstance

@Singleton
class NavService constructor(
    private val navController: NavHostController
) {
    private val routeNavigationDestinationMap: MutableMap<String, NavigationDestination> =
        mutableMapOf()
    private val _currNavigationDestination: MutableState<NavigationDestination> =
        mutableStateOf(NavigationDestination.Sample1)
    val currNavigationDestination: State<NavigationDestination> = _currNavigationDestination

    fun navigate(destination: NavigationDestination, clearBackStack: Boolean = false) {

        val route = buildString {
            append(destination.route)
            destination.navArgs?.forEach { arg ->
                append("/$arg")
            }
        }
        navigate(route, clearBackStack)
    }

    private fun navigate(route: String, clearBackStack: Boolean = false) {
        if (clearBackStack) navController.popBackStack(currNavigationDestination.value.route, true)
        navController.navigate(route)
    }

    init {

        val allNestedClasses = NavigationDestination::class.nestedClasses
        allNestedClasses.forEach { nestedClass ->
            if (nestedClass.isData) {
                val navDest = nestedClass.createInstance() as NavigationDestination
                routeNavigationDestinationMap[navDest.route] = navDest
            } else {
                val navDest = nestedClass.objectInstance as NavigationDestination
                val route = navDest::route.get()
                routeNavigationDestinationMap[route] = navDest
            }
        }
        MainScope().launch {
            navController.currentBackStackEntryFlow.collect {

                _currNavigationDestination.value =
                    routeNavigationDestinationMap[it.destination.route?.takeWhile { currChar -> currChar != '/' }]
                        ?: return@collect
            }
        }
    }
}
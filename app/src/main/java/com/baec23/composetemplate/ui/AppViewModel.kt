package com.baec23.composetemplate.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.baec23.composetemplate.service.SnackbarService
import com.baec23.composetemplate.ui.comp.BottomNavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    val navController: NavHostController,
    snackbarService: SnackbarService,
) : ViewModel() {
    private val _currNavScreenRoute: MutableState<String?> = mutableStateOf(null)

    val snackbarHostState = mutableStateOf(snackbarService.snackbarState)
    val currNavScreenRoute: State<String?> = _currNavScreenRoute
    fun onEvent(event: AppUiEvent) {
        when(event){
            is AppUiEvent.BottomNavBarButtonPressed -> navController.navigate(event.item.route)
        }
    }
    init {
        MainScope().launch {
            navController.currentBackStackEntryFlow.collect{
                _currNavScreenRoute.value = it.destination.route
            }
        }
    }
}

sealed class AppUiEvent {
    data class BottomNavBarButtonPressed(val item: BottomNavigationItem) : AppUiEvent()
}

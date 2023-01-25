package com.baec23.composetemplate.ui.screen.main.firstdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.baec23.composetemplate.navigation.NavService
import com.baec23.composetemplate.ui.screen.DetailsScreen

const val firstDetailsScreenRoute = "first_details_screen_route"
fun NavGraphBuilder.firstDetailsScreen() {
    composable(
        route = "$firstDetailsScreenRoute/{itemId}",
        arguments = listOf(navArgument("itemId") {
            type = NavType.StringType
            nullable = false
        })
    ) {
        val itemId = it.arguments?.getString("itemId")
        itemId?.let {
            val viewModel: FirstDetailsViewModel = hiltViewModel()
            viewModel.setItemId(itemId)
            FirstDetailsScreen(viewModel = viewModel)
        }
    }
}

fun NavService.navigateToFirstDetailsScreen(itemId: String, navOptions: NavOptions? = null) {
    val routeWithArguments = "$firstDetailsScreenRoute/$itemId"
    navController.navigate(route = routeWithArguments, navOptions = navOptions)
}

@Composable
fun FirstDetailsScreen(
    viewModel: FirstDetailsViewModel = hiltViewModel()
) {
    val sampleItem by viewModel.currItem.collectAsState()
    DetailsScreen {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "You selected ${sampleItem?.text}"
            )
        }
    }
}
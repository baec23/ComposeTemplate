package com.baec23.composetemplate.ui.screen.sample2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.sample2Screen(
) {
    composable(
        route = "sample2_route/{nav_arg}",
        arguments = listOf(
            navArgument("nav_arg") { type = NavType.StringType }
        )
    ) {
        val arg = it.arguments?.getString("nav_arg")
        Sample2Screen(navArgument = arg!!)
    }
}

@Composable
fun Sample2Screen(
    navArgument: String,
) {
    Column(modifier = Modifier.padding(16.dp)){
        Text(text = navArgument)
    }
}
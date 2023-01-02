package com.baec23.composetemplate.navigation

sealed class NavigationDestination(
    val route: String,
    val navArgs: List<Any>?,
    val displayName: String = "",
    val shouldShowNavBars: Boolean = true,
) {
    object Sample1 : NavigationDestination(
        route = "sample1_route",
        navArgs = null,
        displayName = "Sample Screen 1"
    )

    data class Sample2(val sampleArg: String = "") : NavigationDestination(
        route = "sample2_route",
        navArgs = listOf(sampleArg),
        displayName = "Sample Screen 2",
    )
}
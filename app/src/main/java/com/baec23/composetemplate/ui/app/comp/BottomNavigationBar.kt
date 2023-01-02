//package com.baec23.composetemplate.ui.app.comp
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.width
//import androidx.compose.material.ripple.LocalRippleTheme
//import androidx.compose.material.ripple.RippleAlpha
//import androidx.compose.material.ripple.RippleTheme
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.NavigationBarItemDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.baec23.hobbybank.navigation.NavScreen
//import com.baec23.hobbybank.navigation.navgraph.BottomNavItem
//
//private object NoRippleTheme : RippleTheme {
//    @Composable
//    override fun defaultColor() = MaterialTheme.colorScheme.onPrimary
//
//    @Composable
//    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.1f)
//}
//
//@Composable
//fun BottomNavigationBar(
//    modifier: Modifier = Modifier,
//    items: List<BottomNavItem>,
//    currScreen: NavScreen,
//    onItemClick: (BottomNavItem) -> Unit
//) {
//    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
//        NavigationBar(
//            modifier = modifier,
//            containerColor = MaterialTheme.colorScheme.onPrimary,
//        ) {
//            items.forEach { item ->
//
//                val isSelected =
//                    if (currScreen.parentRoute != null)
//                        item.screen.route == currScreen.parentRoute
//                    else
//                        item.screen.route == currScreen.route
//
//                NavigationBarItem(
//                    selected = isSelected,
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
//                        indicatorColor = MaterialTheme.colorScheme.primary,
//                        unselectedIconColor = MaterialTheme.colorScheme.onSurface
//                    ),
//                    icon = {
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center
//                        ) {
//                            Row(
//                                modifier = Modifier
//                                    .height(30.dp)
//                                    .fillMaxWidth(0.75f),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.Center
//                            ) {
//                                Icon(imageVector = item.icon, contentDescription = null)
//                                if (isSelected) {
//                                    Spacer(modifier = Modifier.width(5.dp))
//                                    Text(
//                                        text = item.name,
//                                        maxLines = 1,
//                                        overflow = TextOverflow.Clip,
//                                        fontSize = 15.sp
//                                    )
//                                }
//                            }
//
//                        }
//                    },
//                    onClick = {
//                        onItemClick(item)
//                    })
//            }
//        }
//    }
//}

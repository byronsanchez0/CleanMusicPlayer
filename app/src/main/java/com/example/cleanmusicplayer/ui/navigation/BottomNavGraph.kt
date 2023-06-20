package com.example.cleanmusicplayer.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cleanmusicplayer.ui.screens.player.MediaPlayerScreen
import com.example.cleanmusicplayer.ui.screens.search.SearchScreen


@Composable
fun BottomNavGraph(
    navController: NavHostController,
    contentPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Search.route
    ) {
        composable(route = BottomNavItem.Search.route) {
            SearchScreen(modifier = Modifier.padding(contentPadding), navController = navController)
        }
        composable(
            route = BottomNavItem.Player.route,
            arguments = listOf(navArgument("id"){ type = NavType.IntType})
            ) { backStackEntry ->
            backStackEntry.arguments?.getInt("id")?.let { id ->
                MediaPlayerScreen(id)
            }
        }
    }
}


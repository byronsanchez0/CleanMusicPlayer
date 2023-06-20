package com.example.cleanmusicplayer.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Search : BottomNavItem(
        route = "search",
        title = "search",
        icon = Icons.Default.List
    )
    object Player : BottomNavItem(
        route = "player/{id}",
        title = "Player",
        icon = Icons.Default.PlayArrow
    )
}

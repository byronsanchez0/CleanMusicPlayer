package com.example.cleanmusicplayer

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanmusicplayer.ui.navigation.BottomBar
import com.example.cleanmusicplayer.ui.navigation.BottomNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
    ) { innerPadding ->
        BottomNavGraph(
            navController = navController,
            contentPadding = innerPadding
        )
    }
}

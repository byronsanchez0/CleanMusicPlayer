package com.example.cleanmusicplayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cleanmusicplayer.ui.navigation.BottomBar
import com.example.cleanmusicplayer.ui.navigation.BottomNavGraph
import com.example.cleanmusicplayer.ui.screens.search.SearchScreen
import com.example.domain.model.Song

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
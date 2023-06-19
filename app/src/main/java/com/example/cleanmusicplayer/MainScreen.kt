package com.example.cleanmusicplayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cleanmusicplayer.ui.screens.search.SearchScreen
import com.example.domain.model.Song

@Composable
fun MainScreen() {
    Column (Modifier.fillMaxSize()) {
        SearchScreen( )
    }
}
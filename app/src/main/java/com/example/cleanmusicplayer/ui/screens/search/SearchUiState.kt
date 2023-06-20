package com.example.cleanmusicplayer.ui.screens.search

import androidx.media3.common.MediaItem

data class SearchUiState (
    val onSearchChanged: (String) -> Unit = {},
    val onPlaySong: (MediaItem) -> Unit
//    val loading: Boolean
)
package com.example.cleanmusicplayer.ui.screens.search

data class SearchUiState (
    val onSearchChanged: (String) -> Unit = {},
//    val loading: Boolean
)
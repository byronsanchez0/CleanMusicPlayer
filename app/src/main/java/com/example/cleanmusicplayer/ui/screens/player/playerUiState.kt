package com.example.cleanmusicplayer.ui.screens.player

import com.example.cleanmusicplayer.ui.screens.search.utils.ManageUI.UiManage

data class playerUiState (
    val name: String = "",
    val image: String? = "",
    val uiManage: (UiManage) -> Unit,
    val duration: Long = 0L,
    val formatDuration: (Long) -> String = {""},
    val isPlaying: Boolean = false,
    val progress: Float = 0f,
    val progressString: String = "",
    val mediaPlayerStatus: MediaPlayerStatus = MediaPlayerStatus.Initial,
)

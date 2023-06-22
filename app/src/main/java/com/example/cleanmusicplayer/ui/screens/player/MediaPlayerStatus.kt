package com.example.cleanmusicplayer.ui.screens.player

sealed class MediaPlayerStatus{
    object Initial: MediaPlayerStatus()
    object Ready: MediaPlayerStatus()
}

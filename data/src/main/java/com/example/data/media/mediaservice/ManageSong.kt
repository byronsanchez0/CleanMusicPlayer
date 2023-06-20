package com.example.data.media.mediaservice

sealed class ManageSong {

    object PlayPause : ManageSong()
    object Backward : ManageSong()
    object Forward : ManageSong()
    object Stop : ManageSong()
    data class UpdateProgress(val newProgress: Float) : ManageSong()

}


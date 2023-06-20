package com.example.data.media.mediaservice

import androidx.media3.common.Player
import java.util.concurrent.TimeUnit

private const val MIMINUM_VALUE = 0L
fun seekPlayer(player: Player, seconds: Long, isForward: Boolean) {
    val time = TimeUnit.SECONDS.toMillis(seconds)
    val newPosition = if (isForward) {
        (player.currentPosition + time).coerceAtMost(player.duration)
    }else{
        (player.currentPosition - time).coerceAtLeast(MIMINUM_VALUE)
    }
    player.seekTo(newPosition)
}

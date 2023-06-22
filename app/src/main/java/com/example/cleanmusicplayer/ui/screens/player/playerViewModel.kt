package com.example.cleanmusicplayer.ui.screens.player

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.example.cleanmusicplayer.ui.screens.search.utils.uiManage.UiManage
import com.example.data.mediaservice.ManageSong
import com.example.data.mediaservice.MediaServiceManager
import com.example.data.mediaservice.MediaState
import com.example.domain.getSongUseCase
import com.example.domain.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class playerViewModel @Inject constructor(
    private val getSongUseCase: getSongUseCase,
    private val mediaServiceManager: MediaServiceManager

) : ViewModel() {

    private val _song = MutableStateFlow<Song?>(null)
    val _uiState = MutableStateFlow(
        playerUiState(
            image = "",
            name = "",
            uiManage = this::UiManagment,
            duration = 0L,
            progress = 0f,
            progressString = "00.00",
            isPlaying = false
        )

    )

    val uiState = _uiState.asStateFlow()

    init {
        syncState()
    }

    fun fetchAnimeDetails(id: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                val details = getSongUseCase(id)
                val item =
                    MediaItem.Builder().setUri(details?.previews?.preview_hq_mp3).setMediaMetadata(
                        MediaMetadata.Builder()
                            .setArtworkUri(Uri.parse(details?.images?.previewMp3))
                            .build()
                    ).build()
                mediaServiceManager.addMediaItem(item)
                _uiState.value = _uiState.value.copy(
                    name = details.name,
                    image = details.images.previewMp3
                )
            }
            try {
                val details = getSongUseCase(id)
                _song.value = details
            } catch (e: Exception) {
                Log.d("Error", "${e.message}")
            }
        }
    }

    override fun onCleared() {
        viewModelScope.launch {
            mediaServiceManager.onPlayerEvent(ManageSong.Stop)
        }
    }

    fun UiManagment(uiManageSong: UiManage) {
        viewModelScope.launch {
            when (uiManageSong) {
                UiManage.BackwardAction -> mediaServiceManager.onPlayerEvent(ManageSong.Backward)
                UiManage.ForwardAction -> mediaServiceManager.onPlayerEvent(ManageSong.Forward)
                UiManage.PlayBackAction -> mediaServiceManager.onPlayerEvent(ManageSong.PlayPause)
            }
        }
    }

    fun syncState() {
        viewModelScope.launch {
            mediaServiceManager.mediaState.collect { mediaState ->
                when (mediaState) {
                    is MediaState.Buffering -> getTheProgress(mediaState.progress)
                    MediaState.Initial -> _uiState.value =
                        _uiState.value.copy(mediaPlayerStatus = MediaPlayerStatus.Initial)

                    is MediaState.Playing -> _uiState.value =
                        _uiState.value.copy(isPlaying = mediaState.isPlaying)

                    is MediaState.Progress -> getTheProgress(mediaState.progress)
                    is MediaState.Ready -> {
                        _uiState.value = _uiState.value.copy(
                            mediaPlayerStatus = MediaPlayerStatus.Ready,
                            duration = mediaState.duration
                        )
                    }
                }
            }
        }
    }

    private fun formatDuration(duration: Long): String {
        val minutes: Long = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
        val seconds: Long = (TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS)
                - minutes * TimeUnit.SECONDS.convert(ONE_MINUTE, TimeUnit.MINUTES))
        return String.format(DURATION_FORMAT, minutes, seconds)
    }

    private fun getTheProgress(currentProgress: Long) {
        val calculatedProgress =
            if (currentProgress > DEFAULT_PROGRESS)
                (currentProgress.toFloat() / _uiState.value.duration)
            else DEFAULT_PROGRESS_PERCENTAGE
        val calculatedProgressString = formatDuration(currentProgress)
        _uiState.value = uiState.value.copy(
            progress = calculatedProgress,
            progressString = calculatedProgressString
        )
    }

    companion object {
        private const val DURATION_FORMAT = "%02d:%02d"
        private const val DEFAULT_PROGRESS = 0L
        private const val DEFAULT_PROGRESS_PERCENTAGE = 0f
        private const val ONE_MINUTE = 1L
    }
}

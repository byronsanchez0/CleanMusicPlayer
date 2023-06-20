package com.example.cleanmusicplayer.ui.screens.player

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.getSongUseCase
import com.example.domain.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class playerViewModel @Inject constructor(
    private val getSongUseCase: getSongUseCase
) : ViewModel() {

    private val _song = MutableStateFlow<Song?>(null)
    val song: StateFlow<Song?> = _song

    fun fetchAnimeDetails(id: Int) {
        viewModelScope.launch {
            try {
                val details = getSongUseCase(id)
                _song.value = details
            } catch (e: Exception) {
                Log.d("Error","${e.message}")
            }
        }
    }


}
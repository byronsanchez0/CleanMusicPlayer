package com.example.domain

import com.example.domain.model.Song
import javax.inject.Inject

class getSongUseCase @Inject constructor(
    private val songsRepo: SongsRepo
) {
    suspend operator fun invoke(id: Int): Song{
        return songsRepo.getSong(id)
    }
}
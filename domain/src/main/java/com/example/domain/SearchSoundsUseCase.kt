package com.example.domain

import com.example.domain.model.Song
import javax.inject.Inject

class SearchSoundsUseCase @Inject constructor(private val songsRepo: SongsRepo) {
    suspend fun execute(
        page: Int,
        pageSize: Int,
        search: String
    ): List<Song> {
        return songsRepo.getSongs(page, pageSize, search)
    }
}
package com.example.domain

import com.example.domain.model.Song
import javax.inject.Inject

class SearchSoundsUseCase @Inject constructor(private val songsRepo: SongsRepo) {
    suspend fun execute(
        page: Int,
        pageSize: Int,
        search: String?= null
    ): List<Song> {
        return songsRepo.search(page, pageSize, search)
    }
}
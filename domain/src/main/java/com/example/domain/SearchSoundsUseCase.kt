package com.example.domain

import com.example.domain.model.Song
import javax.inject.Inject

class SearchSoundsUseCase @Inject constructor(private val songsRepo: SongsRepo) {
    suspend fun execute(
        search: String,
        page: Int,
        pageSize: Int
    ): List<Song> {
        return songsRepo.searchSongs(
            search,
            page,
            pageSize
        )
    }
}

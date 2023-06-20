package com.example.domain

import com.example.domain.model.Song

interface SongsRepo {
    suspend fun searchSongs(
        search: String,
        page: Int,
        pageSize: Int,

    ): List<Song>

    suspend fun getSong(
        id: Int
    ): Song

}


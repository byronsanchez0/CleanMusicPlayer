package com.example.domain

import com.example.domain.model.Song

interface SongsRepo {
    suspend fun search(
        page: Int,
        pageSize: Int,
        search: String?= null
    ): List<Song>

//    suspend fun searchSongs(
//        pages: Int,
//        search: String
//    ): List<Song>

}


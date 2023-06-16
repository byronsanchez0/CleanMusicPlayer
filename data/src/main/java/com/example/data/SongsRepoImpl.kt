package com.example.data

import com.example.data.mappers.toSong
import com.example.domain.SongsRepo
import com.example.domain.model.Song
import javax.inject.Inject

class SongsRepoImpl @Inject constructor(private val apiService: FreeSoundApiService): SongsRepo {
    override suspend fun search(page: Int, pageSize: Int, search: String): List<Song> {
        val response = apiService.searchSounds(search)
        return response.search.map {it.toSong() }
    }


}
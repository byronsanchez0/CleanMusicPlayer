package com.example.data

import com.example.data.mappers.toSong
import com.example.domain.SongsRepo
import com.example.domain.model.Song
import javax.inject.Inject

class SongsRepoImpl @Inject constructor(private val apiService: FreeSoundApiService): SongsRepo {
    override suspend fun search(page: Int, pageSize: Int, search: String?): List<Song> {
        val response = apiService.searchSounds(search?:"", page.toString(), pageSize.toString())
        if (response.totalResult == 0){  return emptyList()
        }
        return response.search.map {it.toSong() }
    }


}
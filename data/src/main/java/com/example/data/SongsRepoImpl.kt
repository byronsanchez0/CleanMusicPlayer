package com.example.data

import android.util.Log
import com.example.data.mappers.toSong
import com.example.domain.SongsRepo
import com.example.domain.model.Song
import javax.inject.Inject

class SongsRepoImpl @Inject constructor(private val apiService: FreeSoundApiService) : SongsRepo {


    override suspend fun searchSongs(search: String, page: Int, pageSize: Int): List<Song> {
        if (search.isEmpty()) {
            return emptyList()
        }
        val response = apiService.searchSounds(
            search,
            page.toString(),
            pageSize.toString()
        )
        Log.wtf("error", "${response}")
        return response.results.map { it.toSong() }
    }

    override suspend fun getSong(id: Int): Song {
        val response = apiService.getSound(id)
        return response.toSong()
    }
}

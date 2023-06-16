package com.example.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeSoundApiService {
    @GET("apiv2/search/text/")
    suspend fun searchSounds(
        @Query("query") query: String,
        @Query("fields") fields: String = "id, name, previews, images"
    ):SearchResponse
}
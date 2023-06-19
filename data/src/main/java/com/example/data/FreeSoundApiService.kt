package com.example.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeSoundApiService {
    @GET("apiv2/search/text/")
    suspend fun searchSounds(
        @Query("query") query: String,
        @Query("page") page: String,
        @Query("page_size") pageSize: String = "15",
        @Query("fields") fields: String = "id,name,previews,images"
    ):SearchResponse
}
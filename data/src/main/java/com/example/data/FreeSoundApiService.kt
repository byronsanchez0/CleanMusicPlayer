package com.example.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FreeSoundApiService {
    @GET("apiv2/search/text/")
    suspend fun searchSounds(
        @Query("query") query: String,
        @Query("page") page: String,
        @Query("page_size") pageSize: String = "15",
        @Query("fields") fields: String = "id,name,username,previews,images"
    ): SearchResponse

    @GET("apiv2/sounds/{sound_id}")
    suspend fun getSound(
        @Path("sound_id") sound_id: Int
    ): SongResult
}

package com.example.data

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Search")
    val search: List<SongResult>,
    val totalResult:Int,
)

data class SongResult(
    val id: String,
    val name: String,
    val preview: PreviewResult,
    val image: ImageResult
)

data class PreviewResult(
    @SerializedName("preview-hq-mp3")
    val previewR_hq_mp3: String
)
data class ImageResult(
    @SerializedName("waveform_l")
    val previewRMp3: String
)


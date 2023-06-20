package com.example.data

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val results: List<SongResult>,
    val count:Int,
)

data class SongResult(
    val id: String,
    val name: String,
    val username: String,
    val previews: PreviewResult,
    val images: ImageResult
)

data class PreviewResult(
    @SerializedName("preview-hq-mp3")
    val previewR_hq_mp3: String
)
data class ImageResult(
    @SerializedName("waveform_l")
    val waveform_l: String
)


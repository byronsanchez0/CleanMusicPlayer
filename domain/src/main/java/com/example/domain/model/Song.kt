package com.example.domain.model

data class Song(
    val id: String,
    val name: String,
    val username: String,
    val previews: Preview,
    val images: Image
)

data class Preview(
    val preview_hq_mp3: String
)

data class Image(
    val previewMp3: String
)

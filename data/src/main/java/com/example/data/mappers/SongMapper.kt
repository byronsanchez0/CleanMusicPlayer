package com.example.data.mappers

import com.example.data.SearchResponse
import com.example.data.SongResult
import com.example.domain.model.Image
import com.example.domain.model.Preview
import com.example.domain.model.Song

fun SongResult.toSong(): Song{
    return Song(
        id = id,
        name = name,
        preview = Preview(preview.previewR_hq_mp3),
        image = Image(image.previewRMp3)
    )
}
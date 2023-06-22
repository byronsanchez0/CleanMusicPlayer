package com.example.cleanmusicplayer.ui.screens.search.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cleanmusicplayer.R
import com.example.domain.model.Song

@Composable
fun AnimeItem(
    song: Song,
    navController: NavController,
    modifier: Modifier,
    context: Context,
) {
    val painter = rememberAsyncImagePainter(model = song.images.previewMp3)

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .clickable {
                navController.navigate("player/${song.id}")
            },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.aspectRatio(4 / 1f),
                painter = painter,
                contentDescription = "Image",
                alignment = Alignment.Center
            )
            Text(
                text = song.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_8dp))
            )
        }
    }
}

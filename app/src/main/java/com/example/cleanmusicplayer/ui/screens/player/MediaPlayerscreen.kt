package com.example.cleanmusicplayer.ui.screens.player

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.rememberAsyncImagePainter
import com.example.cleanmusicplayer.R

@Composable
fun MediaPlayerScreen(
    id: Int,
    playerViewModel: playerViewModel = hiltViewModel()

) {



    LaunchedEffect(key1 = id) {
        playerViewModel.fetchAnimeDetails(id)
    }
    val songDetails by playerViewModel.song.collectAsState(null)

    songDetails?.let { details ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.onBackground)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = { details.images.previewMp3 }),
                contentDescription = "image",
//            alignment = Alignment.Vertical.,
                modifier = Modifier
                    .scale(1 / 3f)
                    .padding(top = 70.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = details.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                maxLines = 2,
                modifier = Modifier.padding(10.dp)
            )
            PlayerButtons()
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MediaPlayerScreenPreview() {
    val id = 3
    MediaPlayerScreen(id)
}

@Composable
fun PlayerButtons(
//    viewModel: MusicPlayerViewModel,
//context:Context
) {
//    val playBtn by viewModel.playBtn.collectAsState()

//    val player = ExoPlayer.Builder(context)



    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        IconButton(onClick = {
//            viewModel.previousSong()
        }) {
            Icon(
                Icons.Filled.SkipPrevious,
                modifier = Modifier
                    .size(50.dp),
                contentDescription = "previous",
                tint = MaterialTheme.colorScheme.inversePrimary
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { }) {
            Icon(
                Icons.Filled.PlayCircle,
                modifier = Modifier
                    .size(50.dp),
                contentDescription = "previous",
                tint = MaterialTheme.colorScheme.inversePrimary
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
//                viewModel.nextSong()
            }
        ) {
            Icon(
                Icons.Filled.SkipNext,
                modifier = Modifier
                    .size(50.dp),
                contentDescription = "next",
                tint = MaterialTheme.colorScheme.inversePrimary

            )
        }


    }
}



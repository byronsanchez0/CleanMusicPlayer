package com.example.cleanmusicplayer.ui.screens.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.cleanmusicplayer.ui.screens.search.utils.ManageUI.UiManage
import com.example.cleanmusicplayer.R
import com.example.data.media.mediaservice.ManageSong


//fun MediaPlayerScreen(
//    id: Int,
//    playerViewModel: playerViewModel = hiltViewModel(),
//) {
@Composable
fun MediaPlayerScreen(
    id: Int,
    playerViewModel: playerViewModel = hiltViewModel(),
) {

    val uiState by playerViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = id) {
        playerViewModel.fetchAnimeDetails(id = id)
    }
    val painter = rememberAsyncImagePainter(model = uiState.image)
    val progressProvider = Pair(uiState.progress, uiState.progressString)
//    val songDetails by playerViewModel.song.collectAsState(null)


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.onBackground)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painter,
            contentDescription = "image",
//            alignment = Alignment.Vertical.,
            modifier = Modifier
                .size(300.dp)
                .padding(top = 70.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Slider(
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.onSecondary,
                activeTrackColor = MaterialTheme.colorScheme.tertiaryContainer,
                inactiveTrackColor = MaterialTheme.colorScheme.tertiary,
                activeTickColor = MaterialTheme.colorScheme.secondary,
                inactiveTickColor = MaterialTheme.colorScheme.tertiary
            ),
            value = uiState.progress,
            onValueChange = {},
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
//                .padding(horizontal = dimensionResource(id = androidx.compose.foundation.layout.R.dimen.padding_16dp))
        ) {
//            Text(
//                text = uiState.progress.toString(),
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.primary
//            )
            Text(
                text = uiState.progressString,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = uiState.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.inverseOnSurface,
            maxLines = 2,
            modifier = Modifier.padding(10.dp)
        )
        PlayerButtons(uiManage = uiState.uiManage, isPlaying = uiState.isPlaying)
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
//id : Int,
    isPlaying: Boolean,
    uiManage: (UiManage) -> Unit
) {
//    val playBtn by viewModel.playBtn.collectAsState()

//    val player = ExoPlayer.Builder(context)
//    LaunchedEffect(key1 = id) {
//        playerViewModel.fetchAnimeDetails(id = id, playerEvent = playerEvent)
//    }


    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        IconButton(onClick = {
            uiManage(UiManage.BackwardAction)
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
        Image(
            painter = painterResource(id =  if (isPlaying) R.drawable.ic_pause_ else R.drawable.ic_play),
            contentDescription = "Play",
            modifier = Modifier.clickable{
            uiManage(UiManage.PlayBackAction)
        })
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
                uiManage(UiManage.ForwardAction)
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



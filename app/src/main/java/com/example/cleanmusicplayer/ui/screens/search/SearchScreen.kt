package com.example.cleanmusicplayer.ui.screens.search

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.cleanmusicplayer.ui.screens.search.components.SearchBar
import com.example.domain.model.Song

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
//    song: Song,
    modifier: Modifier,
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()

) {
//    val searchQuery = remember { mutableStateOf("") }
//    val searchResults by viewModel.searchResults.collectAsState()

    val songsUiState by viewModel.songsUiState.collectAsState()
    val songList = viewModel.songFlow.collectAsLazyPagingItems()
    val loading = viewModel.isLoading.collectAsState()
//    val onPlaySong = viewModel.

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        SearchScreenContent(
            onSearch = songsUiState.onSearchChanged,
            songList = songList,
            isLoading = loading.value,
            navController = navController,
//            onPlay = songsUiState.onPlaySong
        )
    }
}

@Composable
fun SearchScreenContent(
    onSearch: (String) -> Unit,
    songList: LazyPagingItems<Song>,
    isLoading: Boolean,
    navController: NavHostController,
//    onPlay: (MediaItem) -> Unit
) {
    val context = LocalContext.current
//    var exoPlayer: ExoPlayer
//    exoPlayer = ExoPlayer.Builder(context).build()


    Column {
        SearchBar(onSearch = onSearch)
        LazyColumn(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            items(songList.itemCount) { item ->
                val song = songList[item]
                if (song != null) {
                    AnimeItem(
                        song = song,
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        context = context,
//                        onPlay = onPlay
                    )
                }
            }
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }


}

@Composable
fun AnimeItem(
    song: Song,
    navController: NavController,
    modifier: Modifier,
    context: Context,
//    onPlay: (MediaItem) -> Unit
) {
    val painter = rememberAsyncImagePainter(model = song.images.previewMp3)
//    val aFavAnime = favoriteAnime.contains(anime.id)
    var exoPlayer: ExoPlayer
    exoPlayer = ExoPlayer.Builder(context).build()


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
                modifier = Modifier.aspectRatio(4/1f),
                painter = painter,
                contentDescription = "Image",
                alignment = Alignment.Center
            )

            Text(
                text = song.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                modifier = Modifier.padding(start = 8.dp)
            )
            IconButton(onClick = {
                exoPlayer.setMediaItem(MediaItem.fromUri(song.previews.preview_hq_mp3))
//                onPlay()

                exoPlayer.prepare()
                exoPlayer.play()
            }) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Play"
                )
            }
        }
    }
}

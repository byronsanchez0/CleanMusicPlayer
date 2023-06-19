package com.example.cleanmusicplayer.ui.screens.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.cleanmusicplayer.R
import com.example.cleanmusicplayer.ui.screens.search.components.SearchBar
import com.example.domain.model.Song
import kotlin.math.log

@Composable
fun SearchScreen(
//    song: Song,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiSearchState.collectAsState()
//    val painter = rememberAsyncImagePainter(model = song.image)
    val loading = viewModel.isLoading.collectAsState()
    val songList = viewModel.songFlow.collectAsLazyPagingItems()

    SearchScreenContent(onSearch = uiState.onSearchChanged, songList = songList, isLoading = loading.value)
    println(songList)


}

@Composable
fun SearchScreenContent(
    onSearch: (String) -> Unit,
    songList: LazyPagingItems<Song>,
    isLoading: Boolean,
//    song: Song
) {

//    val painter = rememberAsyncImagePainter(model = song.image)


    Column {
        SearchBar(onSearch = onSearch)
        LazyColumn(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            items(songList.itemCount) { index ->
                val song = songList[index]
                
                println("aaaaa$songList")
                if (song != null) {
                    AnimeItem(
                        song = song,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
        if (isLoading) {
            CircularProgressIndicator()
        }
    }


}

@Composable
fun AnimeItem(
    song: Song,
//    onSelectedFavAnime: (AnimeModel) -> Unit,
//    favoriteAnime: Set<Int>,
//    navController: NavController,
    modifier: Modifier
) {
    val painter = rememberAsyncImagePainter(model = song.image )
//    val aFavAnime = favoriteAnime.contains(anime.id)

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .clickable {
//                navController.navigate("details/${anime.id}")
            },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

                Image(
                    modifier = Modifier.aspectRatio(3 / 4f),
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
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
        }
    }
}

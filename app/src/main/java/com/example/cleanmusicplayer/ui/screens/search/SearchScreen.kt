package com.example.cleanmusicplayer.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.cleanmusicplayer.R
import com.example.cleanmusicplayer.ui.screens.search.components.AnimeItem
import com.example.cleanmusicplayer.ui.screens.search.components.SearchBar
import com.example.cleanmusicplayer.ui.screens.search.utils.rotation.isLandscape
import com.example.domain.model.Song
import kotlinx.coroutines.flow.flowOf

private const val GRID_CELLS_SIZE = 4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()

) {

    val songsUiState by viewModel.songsUiState.collectAsState()
    val songList = viewModel.songFlow.collectAsLazyPagingItems()
    val loading = viewModel.isLoading.collectAsState()

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
        )
    }
}

@Composable
fun SearchScreenContent(
    onSearch: (String) -> Unit,
    songList: LazyPagingItems<Song>,
    isLoading: Boolean,
    navController: NavHostController,
) {
    val context = LocalContext.current

    Column {
        SearchBar(onSearch = onSearch)
        if (isLandscape()) {
            LazyVerticalGrid(columns = GridCells.Fixed(GRID_CELLS_SIZE)) {
                items(songList.itemCount) { item ->
                    val song = songList[item]
                    if (song != null) {
                        AnimeItem(
                            song = song,
                            modifier = Modifier.fillMaxSize(),
                            navController = navController,
                            context = context,
                        )
                    }
                }
            }
        } else
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
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
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenContentPreview() {
    val navcontroller = rememberNavController()
    val isLoading = true
    val fakelist = flowOf(
        PagingData.from(
            listOf(
                Song(
                    id = "3",
                    name = "juan",
                    username = "pedrito",
                    previews = com.example.domain.model.Preview(preview_hq_mp3 = stringResource(R.string.a)),
                    images = com.example.domain.model.Image(previewMp3 = stringResource(R.string.ia))
                )
            )
        )
    ).collectAsLazyPagingItems()
    SearchScreenContent(
        onSearch = { "piano" },
        isLoading = isLoading,
        songList = fakelist,
        navController = navcontroller
    )
}
